package com.example.springjdbc.services;

import com.example.springjdbc.dto.*;
import com.example.springjdbc.exceptions.custom.FailedTransactionRollBackException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
public class AllDataService {
    UserServices userServices;
    ShiftUsersService shiftUsersService;
    ShiftTypesService shiftTypesService;
    ShiftServices shiftServices;
    @Autowired
    public AllDataService(UserServices userServices, ShiftUsersService shiftUsersService, ShiftTypesService shiftTypesService, ShiftServices shiftServices) {
        this.userServices = userServices;
        this.shiftUsersService = shiftUsersService;
        this.shiftTypesService = shiftTypesService;
        this.shiftServices = shiftServices;
    }
    @Transactional(rollbackFor = Exception.class)
    public void insertAllData(AllDTO allDTO) throws FailedTransactionRollBackException{
        try{
            UUID shiftTypeId = shiftTypesService.insertShiftTypes(allDTO.toShiftTypesDto());
            ShiftsDTO shiftsDTO = allDTO.toShiftsDto();
            shiftsDTO.setShiftTypeId(shiftTypeId);
            UUID shiftId = shiftServices.insertShift(shiftsDTO);
            UUID userId = userServices.insertUser(allDTO.toUserDto());
            ShiftUsersDTO shiftUsersDTO = allDTO.toShiftUsersDto();
            shiftUsersDTO.setShiftId(shiftId);
            shiftUsersDTO.setUserId(userId);
            shiftUsersService.insertShiftUsers(shiftUsersDTO);
        } catch(Exception e){
            log.error("Failed to save tenant data. Rolling back transaction.");
            throw new FailedTransactionRollBackException("Failed to save tenant data. Rolling back transaction.");
        }

    }
}
