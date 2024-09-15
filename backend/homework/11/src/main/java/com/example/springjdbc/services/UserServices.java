package com.example.springjdbc.services;

import com.example.springjdbc.dao.UserDAO;
import com.example.springjdbc.dto.UserDTO;
import com.example.springjdbc.entities.User;
import com.example.springjdbc.exceptions.custom.NoSuchIdExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServices {
    private final UserDAO userDAO;
    @Autowired
    UserServices(UserDAO userDAO){
        this.userDAO = userDAO;
    }
    public UUID insertUser(UserDTO userDTO){
        return userDAO.saveUser(userDTO);
    }
    public User fetchUserById(UUID id) throws NoSuchIdExistException {
        return userDAO.getUserById(id);
    }
    public void updateUser(UserDTO userDTO, UUID id) throws NoSuchIdExistException{
        userDAO.updateUser(userDTO,id);
    }
}
