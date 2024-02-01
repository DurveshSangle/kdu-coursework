package com.example.springjpa.controller;

import com.example.springjpa.entities.Shifts;
import com.example.springjpa.services.ShiftsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shifts")
public class ShiftsController {
    private ShiftsServices shiftsServices;
    @Autowired
    public ShiftsController(ShiftsServices shiftsServices) {
        this.shiftsServices = shiftsServices;
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveShifts(@RequestBody Shifts shift){
        shiftsServices.saveShifts(shift);
        return ResponseEntity.ok("added successfully");
    }

    @GetMapping("/topThree")
    public List<Shifts> findTop3ShiftsByDateRange(@RequestParam String startDate , @RequestParam String endDate){
        Date startdate = Date.valueOf(startDate);
        Date enddate = Date.valueOf(endDate);
        return shiftsServices.findTop3ShiftsByDateRange(startdate, enddate);
    }
}
