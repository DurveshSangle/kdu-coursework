package com.example.springjdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AllDTO {
    private final UUID tenantId = UUID.randomUUID();
    //shiftTypes
    private String shiftTypeName;
    private String description;
    private boolean active;
    private String createdBy;
    private String updatedBy;
    private String timeZone;
    //shifts
    private String shiftsName;
    private Date dateStart;
    private Date dateEnd;
    private Time timeStart;
    private Time timeEnd;
    //users
    private String username;
    private boolean loggedIn;

    public ShiftsDTO toShiftsDto(){
        ShiftsDTO shiftsDto = new ShiftsDTO();
        shiftsDto.setName(shiftsName);
        shiftsDto.setCreatedBy(createdBy);
        shiftsDto.setUpdatedBy(updatedBy);
        shiftsDto.setDateEnd(dateEnd);
        shiftsDto.setDateEnd(dateStart);
        shiftsDto.setTimeZone(timeZone);
        shiftsDto.setTimeStart(timeStart);
        shiftsDto.setTimeEnd(timeEnd);
        shiftsDto.setTenantId(tenantId);
        return shiftsDto;
    }

    public ShiftTypesDTO toShiftTypesDto(){
        ShiftTypesDTO shiftTypesDto = new ShiftTypesDTO();
        shiftTypesDto.setName(shiftsName);
        shiftTypesDto.setCreatedBy(createdBy);
        shiftTypesDto.setUpdatedBy(updatedBy);
        shiftTypesDto.setTimeZone(timeZone);
        shiftTypesDto.setActive(active);
        shiftTypesDto.setDescription(description);
        shiftTypesDto.setTenantId(tenantId);
        return shiftTypesDto;
    }

    public ShiftUsersDTO toShiftUsersDto(){
        ShiftUsersDTO shiftUsersDto = new ShiftUsersDTO();
        shiftUsersDto.setCreatedBy(createdBy);
        shiftUsersDto.setUpdatedBy(updatedBy);
        shiftUsersDto.setTenantId(tenantId);
        return shiftUsersDto;
    }

    public UserDTO toUserDto(){
        UserDTO userDto = new UserDTO();
        userDto.setCreatedBy(createdBy);
        userDto.setUpdatedBy(updatedBy);
        userDto.setTimeZone(timeZone);
        userDto.setUsername(username);
        userDto.setLoggedIn(loggedIn);
        userDto.setTenantId(tenantId);
        return userDto;
    }
}
