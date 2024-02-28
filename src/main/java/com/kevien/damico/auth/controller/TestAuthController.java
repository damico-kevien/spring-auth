package com.kevien.damico.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(("/api/v1/test"))
public class TestAuthController {

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello! You're authorized!");
    }

}
