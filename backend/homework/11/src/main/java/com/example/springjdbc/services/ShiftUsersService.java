package com.example.springjdbc.services;

import com.example.springjdbc.dao.ShiftUsersDAO;
import com.example.springjdbc.dto.ShiftUsersDTO;
import com.example.springjdbc.entities.ShiftUsers;
import com.example.springjdbc.exceptions.custom.NoSuchIdExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShiftUsersService {
    private final ShiftUsersDAO shiftUsersDAO;
    @Autowired
    public ShiftUsersService(ShiftUsersDAO shiftUsersDAO){
        this.shiftUsersDAO = shiftUsersDAO;
    }
    public UUID insertShiftUsers(ShiftUsersDTO shiftUsersDTO){
        return shiftUsersDAO.saveShiftUsers(shiftUsersDTO);
    }
    public ShiftUsers fetchShiftUsers(UUID id) throws NoSuchIdExistException {
        return shiftUsersDAO.getShiftUsersById(id);
    }
}
