package com.example.springjdbc.controller;

import com.example.springjdbc.dto.ShiftUsersDTO;
import com.example.springjdbc.entities.ShiftUsers;
import com.example.springjdbc.exceptions.custom.NoSuchIdExistException;
import com.example.springjdbc.services.ShiftUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/userShift")
public class ShiftUsersController {
    private final ShiftUsersService shiftUsersService;
    @Autowired
    public ShiftUsersController(ShiftUsersService shiftUsersService){
        this.shiftUsersService = shiftUsersService;
    }
    @PostMapping("/insert")
    public ResponseEntity<String> insertShiftUsers(@RequestBody ShiftUsersDTO shiftUsersDTO){
        UUID shiftUSerId = shiftUsersService.insertShiftUsers(shiftUsersDTO);
        return ResponseEntity.ok("Successfully inserted ShiftUser data in database. \nShift_User_Id is "+shiftUSerId);
    }
    @GetMapping("/fetch/{id}")
    public ResponseEntity<ShiftUsers> fetchShiftTypes(@PathVariable String id) throws NoSuchIdExistException {
        UUID uuid = UUID.fromString(id);
        return ResponseEntity.ok(shiftUsersService.fetchShiftUsers(uuid));
    }
}
