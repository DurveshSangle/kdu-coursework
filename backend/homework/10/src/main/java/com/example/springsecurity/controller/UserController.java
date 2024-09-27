package com.example.springsecurity.controller;

import com.example.springsecurity.dto.UserDTO;
import com.example.springsecurity.exception.custom.NoSuchUSerException;
import com.example.springsecurity.model.User;
import com.example.springsecurity.service.Mapper;
import com.example.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;


import java.util.List;

@Slf4j
@RestController
public class UserController {
    private final UserService userService;
    private final Mapper mapper;
    @Autowired
    UserController(UserService userService, Mapper mapper){
        this.userService = userService;
        this.mapper = mapper;
    }

    /**
     * Retrieve a list of users from the repository.
     *
     * @return A list of {@link User} objects.
     */
    @GetMapping("/userList")
    public List<User> getUserList(){
        return userService.getUsersListFromRepo();
    }

    /**
     * Add a new user to the repository based on the provided user data.
     *
     * @param userDTO The data for creating a new user, represented as a {@link UserDTO} object.
     * @return A {@link ResponseEntity} indicating the success of the operation.
     */
    @PostMapping("/newUser")
    public ResponseEntity<String> addNewUser(@RequestBody UserDTO userDTO){
        User user = mapper.userDtoToUser(userDTO);
        userService.addUserToRepo(user);
        return ResponseEntity.ok("User added successfully");
    }

    /**
     * Retrieve a user by their username from the repository.
     *
     * @param userName The username of the user to retrieve.
     * @return A {@link UserDTO} object representing the user's data.
     * @throws NoResourceFoundException If no user is found with the given username.
     */
    @GetMapping("/getUser")
    public UserDTO getUserByName(@RequestParam String userName) throws NoResourceFoundException {
        try{
            User user = userService.getUserByName(userName);
            return mapper.userToUserDto(user);
        }catch(NoSuchUSerException e){
            log.error("No such user exists!!");
            throw new NoResourceFoundException(HttpMethod.GET,"/getUser");
        }
    }
}
