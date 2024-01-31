package com.example.springjdbc.mapper;

import com.example.springjdbc.entities.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        User user = new User();
        user.setId(UUID.fromString(rs.getString("id")));
        user.setCreatedAt(rs.getTimestamp("created_at").toInstant());
        Timestamp timestamp = rs.getTimestamp("updated_at");
        Instant instant = null;
        if(timestamp!=null) instant = timestamp.toInstant();
        user.setUpdatedAt(instant);
        user.setCreatedBy(rs.getString("created_by"));
        user.setUpdatedBy(rs.getString("updated_by"));
        user.setLoggedIn(rs.getBoolean(("loggedIn")));
        user.setTimeZone(rs.getString("time_zone"));
        user.setUsername(rs.getString("username"));
        user.setTenantId(UUID.fromString(rs.getString("tenant_id")));
        return user;
    }
}
