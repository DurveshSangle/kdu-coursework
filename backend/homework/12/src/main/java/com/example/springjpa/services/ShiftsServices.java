package com.example.springjpa.services;

import com.example.springjpa.entities.Shifts;
import com.example.springjpa.repositories.ShiftsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ShiftsServices {
    private ShiftsRepository shiftsRepository;
    @Autowired
    public ShiftsServices(ShiftsRepository shiftsRepository) {
        this.shiftsRepository = shiftsRepository;
    }
    public void saveShifts(Shifts shift){
        shiftsRepository.save(shift);
        log.info("Shift saved successfully");
    }

    public List<Shifts> findTop3ShiftsByDateRange(Date startDate, Date endDate){
        return shiftsRepository.findTop3ShiftsByDateRange(startDate, endDate);
    }
}
