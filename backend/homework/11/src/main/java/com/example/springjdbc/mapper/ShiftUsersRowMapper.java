package com.example.springjdbc.mapper;

import com.example.springjdbc.entities.ShiftUsers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class ShiftUsersRowMapper implements RowMapper<ShiftUsers> {
    @Override
    public ShiftUsers mapRow(ResultSet rs, int rowNum) throws SQLException{
        ShiftUsers shiftUser = new ShiftUsers();
        shiftUser.setId(UUID.fromString(rs.getString("id")));
        shiftUser.setUserId(UUID.fromString(rs.getString("user_id")));
        shiftUser.setShiftId(UUID.fromString(rs.getString("shift_id")));
        shiftUser.setCreatedAt(rs.getTimestamp("created_at").toInstant());
        Timestamp timestamp = rs.getTimestamp("updated_at");
        Instant instant = null;
        if(timestamp!=null) instant = timestamp.toInstant();
        shiftUser.setUpdatedAt(instant);
        shiftUser.setCreatedBy(rs.getString("created_by"));
        shiftUser.setUpdatedBy(rs.getString("updated_by"));
        shiftUser.setTenantId(UUID.fromString(rs.getString("tenant_id")));
        return shiftUser;
    }
}
