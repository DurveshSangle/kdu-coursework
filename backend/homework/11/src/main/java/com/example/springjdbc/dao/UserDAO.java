package com.example.springjdbc.dao;

import com.example.springjdbc.dto.UserDTO;
import com.example.springjdbc.entities.User;
import com.example.springjdbc.exceptions.custom.NoSuchIdExistException;
import com.example.springjdbc.mapper.UserRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Slf4j
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    UserDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final String ERRORMSG = "Invalid User Id!!!";
    public UUID generateUUID(){
        return UUID.randomUUID();
    }
    public UUID saveUser(UserDTO userDTO){
        String sql = "INSERT INTO users (id, username, time_zone, created_by, updated_by, updated_at, tenant_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        UUID id = generateUUID();
        jdbcTemplate.update(sql, id, userDTO.getUsername(), userDTO.getTimeZone(), userDTO.getCreatedBy(), userDTO.getUpdatedBy(), null, userDTO.getTenantId());
        return id;
    }
    public User getUserById(UUID id) throws NoSuchIdExistException{
        String sql = "SELECT * FROM users WHERE id = ?";
        try{
            return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
        }catch(EmptyResultDataAccessException e){
            log.error(ERRORMSG);
            throw new NoSuchIdExistException(ERRORMSG);
        }
    }
    public void updateUser(UserDTO userDTO, UUID id) throws NoSuchIdExistException{
        String sql = "UPDATE users SET username=?, loggedin=?, time_zone=?, updated_by=?, updated_at=CURRENT_TIMESTAMP, tenant_id=? WHERE id=?";
        int loggedIn = 0;
        if(userDTO.isLoggedIn()) loggedIn=1;
        try{
            jdbcTemplate.update(sql,userDTO.getUsername(),loggedIn,userDTO.getTimeZone(),userDTO.getUpdatedBy(),userDTO.getTenantId(),id);
        }catch(EmptyResultDataAccessException e){
            log.error(ERRORMSG);
            throw new NoSuchIdExistException(ERRORMSG);
        }
    }
}
