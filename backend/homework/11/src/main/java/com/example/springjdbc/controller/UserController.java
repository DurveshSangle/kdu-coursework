package com.example.springjdbc.controller;

import com.example.springjdbc.dto.UserDTO;
import com.example.springjdbc.entities.User;
import com.example.springjdbc.exceptions.custom.NoSuchIdExistException;
import com.example.springjdbc.services.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {
    private final UserServices userServices;
    @Autowired
    public UserController(UserServices userService){
        this.userServices = userService;
    }
    @PostMapping("/insert")
    public ResponseEntity<String> insertUser(@RequestBody UserDTO userDTO){
        UUID userId = userServices.insertUser(userDTO);
        return ResponseEntity.ok("Successfully inserted user in the database. \nUser Id is "+userId);
    }
    @GetMapping("/fetch/{id}")
    public ResponseEntity<User> fetchUserById(@PathVariable String id) throws NoSuchIdExistException{
        UUID uid = UUID.fromString(id);
        return ResponseEntity.ok(userServices.fetchUserById(uid));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO,@PathVariable String id) throws NoSuchIdExistException {
        UUID uuid = UUID.fromString(id);
        userServices.updateUser(userDTO,uuid);
        return ResponseEntity.ok("User updated successfully");
    }
}
