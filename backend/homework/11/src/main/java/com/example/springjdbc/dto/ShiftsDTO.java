package com.example.springjdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiftsDTO {
    private UUID shiftTypeId;
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private Time timeStart;
    private Time timeEnd;
    private String createdBy;
    private String updatedBy;
    private String timeZone;
    private UUID tenantId;
}
