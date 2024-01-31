package com.example.springjdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private boolean loggedIn;
    private String createdBy;
    private String updatedBy;
    private String timeZone;
    private UUID tenantId;
}
