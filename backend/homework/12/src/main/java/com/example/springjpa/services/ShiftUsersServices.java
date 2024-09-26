package com.example.springjpa.services;

import com.example.springjpa.entities.ShiftUsers;
import com.example.springjpa.exceptions.custom.NoSuchUserExistException;
import com.example.springjpa.repositories.ShiftUsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ShiftUsersServices {
    private ShiftUsersRepository shiftUsersRepository;
    @Autowired
    public ShiftUsersServices(ShiftUsersRepository shiftUsersRepository) {
        this.shiftUsersRepository = shiftUsersRepository;
    }
    public void saveShiftUsers(ShiftUsers shiftUsers){
        shiftUsersRepository.save(shiftUsers);
    }

    public ShiftUsers findShiftUserByIdAndEndTime(UUID userId) throws NoSuchUserExistException{
        Optional<ShiftUsers> shiftUserOptional = shiftUsersRepository.findById(userId);
        if (!shiftUserOptional.isPresent()) {
            throw new NoSuchUserExistException("No shift user exist with given ID");
        }
        log.info("Found User with id: "+ userId.toString());
        return shiftUserOptional.get();
    }
    public void deleteShiftUser(ShiftUsers shiftUsers){
        shiftUsersRepository.delete(shiftUsers);
        log.info("ShiftUser deleted successfully");
    }
}
