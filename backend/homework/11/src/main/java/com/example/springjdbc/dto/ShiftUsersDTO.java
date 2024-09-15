package com.example.springjdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiftUsersDTO {
    private UUID shiftId;
    private UUID userId;
    private UUID tenantId;
    private String createdBy;
    private String updatedBy;
}
