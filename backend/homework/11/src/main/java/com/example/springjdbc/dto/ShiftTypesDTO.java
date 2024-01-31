package com.example.springjdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiftTypesDTO {
    private String name;
    private String description;
    private boolean active;
    private String createdBy;
    private String updatedBy;
    private String timeZone;
    private UUID tenantId;
}
