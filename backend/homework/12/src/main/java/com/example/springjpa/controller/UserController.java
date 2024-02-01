package com.example.springjpa.controller;

import com.example.springjpa.entities.Users;
import com.example.springjpa.exceptions.custom.NoSuchUserExistException;
import com.example.springjpa.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    UsersServices usersServices;
    @Autowired
    public UserController(UsersServices usersServices) {
        this.usersServices = usersServices;
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveUsers(@RequestBody Users user){
        usersServices.saveUser(user);
        return ResponseEntity.ok("added successfully");
    }

    @GetMapping("/allusers")
    public Page<Users> findAllUsers (@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        page = Math.max(0, page);
        size = Math.min(50, Math.max(1, size));
        PageRequest pageRequest = PageRequest.of(page, size);
        return usersServices.findAll(pageRequest);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody Users user) throws NoSuchUserExistException {
        UUID userUuid = UUID.fromString(userId);
        usersServices.updateUser(user,userUuid);
        return ResponseEntity.ok("Updated successfully");
    }
}
