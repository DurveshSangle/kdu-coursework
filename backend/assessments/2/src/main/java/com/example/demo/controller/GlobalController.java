package com.example.demo.controller;

import com.example.demo.entities.Users;
import com.example.demo.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class GlobalController {
    UsersServices usersServices;
    @Autowired
    public GlobalController(UsersServices usersServices) {
        this.usersServices = usersServices;
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Users user){
        usersServices.saveUser(user);
        return ResponseEntity.ok("User Registered !!");
    }
    @GetMapping("/login")
    public ResponseEntity<String> loginUser(){
        String responseMessage = "Test login successful";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
}
