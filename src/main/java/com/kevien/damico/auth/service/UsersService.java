package com.kevien.damico.auth.service;

import com.kevien.damico.auth.model.User;
import com.kevien.damico.auth.repository.UsersRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository repository;
    private final PasswordEncoder encoder;

    public UsersService(UsersRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public ResponseEntity<User> createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
