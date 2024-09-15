package com.example.springjdbc.services;

import com.example.springjdbc.dao.ShiftsDAO;
import com.example.springjdbc.dto.ShiftsDTO;
import com.example.springjdbc.entities.Shifts;
import com.example.springjdbc.exceptions.custom.NoSuchIdExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShiftServices {
    private final ShiftsDAO shiftsDAO;
    @Autowired
    public ShiftServices(ShiftsDAO shiftsDAO){
        this.shiftsDAO =shiftsDAO;
    }
    public UUID insertShift(ShiftsDTO shiftsDTO){
        return shiftsDAO.saveShifts(shiftsDTO);
    }
    public Shifts fetchShifts(UUID id) throws NoSuchIdExistException {
        return shiftsDAO.getShiftById(id);
    }
}
