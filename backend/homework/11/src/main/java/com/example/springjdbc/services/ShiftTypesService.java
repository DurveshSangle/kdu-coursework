package com.example.springjdbc.services;

import com.example.springjdbc.dao.ShiftTypesDAO;
import com.example.springjdbc.dto.ShiftTypesDTO;
import com.example.springjdbc.entities.ShiftTypes;
import com.example.springjdbc.exceptions.custom.NoSuchIdExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShiftTypesService {
    private final ShiftTypesDAO shiftTypesDAO;
    @Autowired
    public ShiftTypesService(ShiftTypesDAO shiftTypesDAO){
        this.shiftTypesDAO = shiftTypesDAO;
    }
    public UUID insertShiftTypes(ShiftTypesDTO shiftTypesDTO){
        return shiftTypesDAO.saveShiftTypes(shiftTypesDTO);
    }
    public ShiftTypes fetchShiftTypes(UUID uuid) throws NoSuchIdExistException {
        return shiftTypesDAO.getShiftById(uuid);
    }
}
