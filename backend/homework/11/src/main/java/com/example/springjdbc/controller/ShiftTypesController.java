package com.example.springjdbc.controller;

import com.example.springjdbc.dto.ShiftTypesDTO;
import com.example.springjdbc.entities.ShiftTypes;
import com.example.springjdbc.exceptions.custom.NoSuchIdExistException;
import com.example.springjdbc.services.ShiftTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/shiftTypes")
public class ShiftTypesController {
    private final ShiftTypesService shiftTypesService;
    @Autowired
    public ShiftTypesController(ShiftTypesService shiftTypesService){
        this.shiftTypesService= shiftTypesService;
    }
    @PostMapping("/insert")
    public ResponseEntity<String> insertShiftTypes(@RequestBody ShiftTypesDTO shiftTypesDTO){
        UUID shiftTypeId = shiftTypesService.insertShiftTypes(shiftTypesDTO);
        return ResponseEntity.ok("Successfully inserted Shift Type data in database. \nShift_Type_id is "+shiftTypeId);
    }
    @GetMapping("/fetch/{id}")
    public ResponseEntity<ShiftTypes> fetchShiftTypes(@PathVariable String id) throws NoSuchIdExistException {
        UUID uuid = UUID.fromString(id);
        return ResponseEntity.ok(shiftTypesService.fetchShiftTypes(uuid));
    }
}
