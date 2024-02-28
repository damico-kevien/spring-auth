package com.kevien.damico.auth.controller;

import com.kevien.damico.auth.model.User;
import com.kevien.damico.auth.security.JwtTokenUtil;
import com.kevien.damico.auth.service.BlackListService;
import com.kevien.damico.auth.service.UsersService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    private final UsersService usersService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final BlackListService blackListService;

    public AuthController(UsersService usersService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, BlackListService blackListService) {
        this.usersService = usersService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.blackListService = blackListService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestBody User user) {
        return usersService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLogin userLogin) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.username, userLogin.password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtObject jwt = new JwtObject(jwtTokenUtil.generateToken(userDetails), jwtTokenUtil.generateRefreshToken(userDetails));
        return ResponseEntity.ok(jwt);
    }

    @PutMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody JwtObject jwtObject) {
        blackListService.blackListJwt(jwtObject.jwt());
        blackListService.blackListJwt(jwtObject.refreshToken());
        return ResponseEntity.ok(null);
    }

    // TODO: PASSWORD RECOVERY

    private record JwtObject(String jwt, String refreshToken){

    }

    private record UserLogin(String username, String password) {

    }
}
