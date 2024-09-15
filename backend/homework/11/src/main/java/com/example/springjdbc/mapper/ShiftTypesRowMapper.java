package com.example.springjdbc.mapper;

import com.example.springjdbc.entities.ShiftTypes;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class ShiftTypesRowMapper implements RowMapper<ShiftTypes> {

    @Override
    public ShiftTypes mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShiftTypes shiftTypes = new ShiftTypes();
        shiftTypes.setId(UUID.fromString(rs.getString("id")));
        shiftTypes.setName(rs.getString("uq_name"));
        shiftTypes.setDescription(rs.getString("description"));
        shiftTypes.setActive(rs.getBoolean("active"));
        shiftTypes.setCreatedAt(rs.getTimestamp("created_at").toInstant());
        Timestamp timestamp = rs.getTimestamp("updated_at");
        Instant instant = null;
        if(timestamp!=null) instant = timestamp.toInstant();
        shiftTypes.setUpdatedAt(instant);
        shiftTypes.setCreatedBy(rs.getString("created_by"));
        shiftTypes.setUpdatedBy(rs.getString("updated_by"));
        shiftTypes.setTimeZone(rs.getString("time_zone"));
        shiftTypes.setTenantId(UUID.fromString(rs.getString("tenant_id")));
        return shiftTypes;
    }
}
