package com.example.springsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    /**
     * Handles the login request for testing purposes.
     *
     * @return A {@link ResponseEntity} with a success message and HTTP status code 201 (Created).
     */
    @GetMapping("/person/login")
    public ResponseEntity<String> login() {
        String responseMessage = "Test login successful";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
}
