package com.example.springjpa.services;

import com.example.springjpa.entities.ShiftTypes;
import com.example.springjpa.repositories.ShiftTypesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShiftTypesServices {
    private ShiftTypesRepository shiftTypesRepository;
    @Autowired
    public ShiftTypesServices(ShiftTypesRepository shiftTypesRepository) {
        this.shiftTypesRepository = shiftTypesRepository;
    }
    public void saveShiftType(ShiftTypes shiftTypes){
        shiftTypesRepository.save(shiftTypes);
        log.info("Shift Type saved successfully");
    }
}
