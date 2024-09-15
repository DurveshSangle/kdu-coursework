package com.example.springjdbc.dao;

import com.example.springjdbc.dto.ShiftsDTO;
import com.example.springjdbc.entities.Shifts;
import com.example.springjdbc.exceptions.custom.NoSuchIdExistException;
import com.example.springjdbc.mapper.ShiftRowMapper;
import com.example.springjdbc.mapper.UserRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Slf4j
public class ShiftsDAO {
    private final JdbcTemplate jdbcTemplate;
    private static final String ERRORMSG = "Invalid User Id!!!";
    @Autowired
    ShiftsDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public UUID generateUUID(){
        return UUID.randomUUID();
    }
    public UUID saveShifts(ShiftsDTO shiftsDTO){
        String sql = "INSERT INTO public.shifts(id, shift_type_id, name, date_start, date_end, time_start, time_end, created_by, updated_by, updated_at, time_zone, tenant_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        UUID id = generateUUID();
        jdbcTemplate.update(sql,id,shiftsDTO.getShiftTypeId(),shiftsDTO.getName(),shiftsDTO.getDateStart(), shiftsDTO.getDateEnd(),shiftsDTO.getTimeStart(),shiftsDTO.getTimeEnd(),shiftsDTO.getCreatedBy(),shiftsDTO.getUpdatedBy(),null,shiftsDTO.getTimeZone(),shiftsDTO.getTenantId());
        return id;
    }
    public Shifts getShiftById(UUID id) throws NoSuchIdExistException {
        String sql = "SELECT * FROM shifts WHERE id=?";
        try{
            return jdbcTemplate.queryForObject(sql,new ShiftRowMapper(),id);
        }catch(EmptyResultDataAccessException e){
            log.error(ERRORMSG);
            throw new NoSuchIdExistException(ERRORMSG);
        }
    }
}
