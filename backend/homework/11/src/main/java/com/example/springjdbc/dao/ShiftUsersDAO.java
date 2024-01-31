package com.example.springjdbc.dao;

import com.example.springjdbc.dto.ShiftUsersDTO;
import com.example.springjdbc.entities.ShiftUsers;
import com.example.springjdbc.exceptions.custom.NoSuchIdExistException;
import com.example.springjdbc.mapper.ShiftUsersRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Slf4j
@Repository
public class ShiftUsersDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    ShiftUsersDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public UUID generateUUID(){
        return UUID.randomUUID();
    }
    private static final String ERRORMSG = "Invalid User Id!!!";
    public UUID saveShiftUsers(ShiftUsersDTO shiftUsersDTO){
        String sql = "INSERT INTO shift_users(id,shift_id, user_id, created_by, updated_by, updated_at, tenant_id) VALUES(?,?,?,?,?,?,?)";
        UUID id = generateUUID();
        jdbcTemplate.update(sql,id,shiftUsersDTO.getShiftId(),shiftUsersDTO.getUserId(),shiftUsersDTO.getCreatedBy(),shiftUsersDTO.getUpdatedBy(),null,shiftUsersDTO.getTenantId());
        return id;
    }
    public ShiftUsers getShiftUsersById(UUID id) throws NoSuchIdExistException{
        String sql = "SELECT * FROM shift_users WHERE id=?";
        try{
            return jdbcTemplate.queryForObject(sql,new ShiftUsersRowMapper(),id);
        }catch(EmptyResultDataAccessException e){
            log.error(ERRORMSG);
            throw new NoSuchIdExistException(ERRORMSG);
        }
    }
}
