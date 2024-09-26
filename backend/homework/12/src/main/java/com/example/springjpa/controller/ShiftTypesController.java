package com.example.springjpa.controller;

import com.example.springjpa.entities.ShiftTypes;
import com.example.springjpa.services.ShiftTypesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shiftTypes")
public class ShiftTypesController {
    private ShiftTypesServices shiftTypesServices;
    @Autowired
    public ShiftTypesController(ShiftTypesServices shiftTypesServices) {
        this.shiftTypesServices = shiftTypesServices;
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveShiftsType(ShiftTypes shiftTypes){
        shiftTypesServices.saveShiftType(shiftTypes);
        return ResponseEntity.ok("added successfully");
    }
}
