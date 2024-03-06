package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.ResponseDto;
import com.kdu.smarthome.entities.Users;
import com.kdu.smarthome.services.UserServices;
import com.kdu.smarthome.utils.JWTGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class UserController {
    UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    /**
     * Registers a new user and generates a JWT token for authentication.
     *
     * @param user The user information for registration.
     * @return ResponseEntity containing the response for user registration and the generated JWT token.
     */
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerUser(@RequestBody Users user) {
        userServices.saveUserInRepo(user);
        String jwt = JWTGenerator.generateJWT(user.getUsername());
        ResponseDto responseDto = new ResponseDto("user is registered",jwt);
        log.info("User registered successfully!!");
        return ResponseEntity.ok(responseDto);
    }
}
