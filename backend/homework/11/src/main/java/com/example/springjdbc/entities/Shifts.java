package com.example.springjdbc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shifts {
    private UUID id;
    private UUID shiftTypeId;
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private Time timeStart;
    private Time timeEnd;
    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
    private String timeZone;
    private UUID tenantId;
}
