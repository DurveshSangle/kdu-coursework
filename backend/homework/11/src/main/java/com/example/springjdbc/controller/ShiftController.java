package com.example.springjdbc.controller;

import com.example.springjdbc.dto.ShiftsDTO;
import com.example.springjdbc.entities.Shifts;
import com.example.springjdbc.exceptions.custom.NoSuchIdExistException;
import com.example.springjdbc.services.ShiftServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/shift/")
public class ShiftController {
    private final ShiftServices shiftServices;
    @Autowired
    public ShiftController(ShiftServices shiftServices){
        this.shiftServices = shiftServices;
    }
    @PostMapping("/insert")
    public ResponseEntity<String> insertShift(@RequestBody ShiftsDTO shiftsDTO){
        UUID shiftId = shiftServices.insertShift(shiftsDTO);
        return ResponseEntity.ok("Successfully inserted Shift data in database. \nShift_Id is "+shiftId);
    }
    @GetMapping("/fetch/{id}")
    public ResponseEntity<Shifts> fetchShift(@PathVariable String id) throws NoSuchIdExistException {
        UUID uuid = UUID.fromString(id);
        return ResponseEntity.ok(shiftServices.fetchShifts(uuid));
    }
}
