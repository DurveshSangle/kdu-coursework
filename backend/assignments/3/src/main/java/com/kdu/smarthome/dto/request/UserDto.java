package com.kdu.smarthome.dto.request;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String name;
    private String firstName;
    private String lastName;
    private String emailId;
}
