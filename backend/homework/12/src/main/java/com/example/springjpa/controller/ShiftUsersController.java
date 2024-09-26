package com.example.springjpa.controller;

import com.example.springjpa.entities.ShiftUsers;
import com.example.springjpa.exceptions.custom.NoSuchUserExistException;
import com.example.springjpa.services.ShiftUsersServices;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/shiftUsers")
public class ShiftUsersController {
    private ShiftUsersServices shiftUsersServices;
    @Autowired
    public ShiftUsersController(ShiftUsersServices shiftUsersServices) {
        this.shiftUsersServices = shiftUsersServices;
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveShiftUsers(ShiftUsers shiftUsers){
        shiftUsersServices.saveShiftUsers(shiftUsers);
        return ResponseEntity.ok("added successfully");
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteShiftUser(@PathVariable String userId) throws BadRequestException {
        UUID userUuid = UUID.fromString(userId);
        try{
            ShiftUsers shiftUsers = shiftUsersServices.findShiftUserByIdAndEndTime(userUuid);
            shiftUsersServices.deleteShiftUser(shiftUsers);
        } catch(NoSuchUserExistException e){
            throw new BadRequestException("Invalid User UUID");
        }
        return ResponseEntity.ok("ShiftUser deleted successfully");
    }
}
