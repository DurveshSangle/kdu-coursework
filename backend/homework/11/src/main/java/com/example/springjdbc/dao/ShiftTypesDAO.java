package com.example.springjdbc.dao;

import com.example.springjdbc.dto.ShiftTypesDTO;
import com.example.springjdbc.entities.ShiftTypes;
import com.example.springjdbc.exceptions.custom.NoSuchIdExistException;
import com.example.springjdbc.mapper.ShiftTypesRowMapper;
import com.example.springjdbc.mapper.UserRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Slf4j
@Repository
public class ShiftTypesDAO {
    private final JdbcTemplate jdbcTemplate;
    private static final String ERRORMSG = "Invalid User Id!!!";
    @Autowired
    ShiftTypesDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public UUID generateUUID(){
        return UUID.randomUUID();
    }

    public UUID saveShiftTypes(ShiftTypesDTO shiftTypesDTO){
        String sql = "INSERT INTO shift_types(id, uq_name, description, active, created_by, updated_by, updated_at, time_zone, tenant_id) VALUES(?,?,?,?,?,?,?,?,?)";
        UUID id = generateUUID();
        jdbcTemplate.update(sql,id,shiftTypesDTO.getName(),shiftTypesDTO.getDescription(),shiftTypesDTO.isActive(),shiftTypesDTO.getCreatedBy(),shiftTypesDTO.getUpdatedBy(),null,shiftTypesDTO.getTimeZone(),shiftTypesDTO.getTenantId());
        return id;
    }
    public ShiftTypes getShiftById(UUID id) throws NoSuchIdExistException{
        String sql = "SELECT * FROM shift_types WHERE id=?";
        try{
            return jdbcTemplate.queryForObject(sql,new ShiftTypesRowMapper(),id);
        }catch(EmptyResultDataAccessException e){
            log.error(ERRORMSG);
            throw new NoSuchIdExistException(ERRORMSG);
        }
    }
}
