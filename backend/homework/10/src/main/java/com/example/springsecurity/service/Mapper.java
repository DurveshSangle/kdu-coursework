package com.example.springsecurity.service;

import com.example.springsecurity.dto.UserDTO;
import com.example.springsecurity.model.User;
import org.springframework.stereotype.Service;

@Service
public class Mapper {
    /** Function to convert UserDTO to User */
    public User userDtoToUser(UserDTO userDTO){
        return new User(userDTO.getUserName(), userDTO.getPassword(), userDTO.getEmail());
    }

    /** Function to convert User to UserDTO */
    public UserDTO userToUserDto(User user){
        return new UserDTO(user.getUsername(), user.getPassword(), user.getEmail());
    }
}
