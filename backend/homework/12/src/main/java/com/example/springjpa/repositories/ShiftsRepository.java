package com.example.springjpa.repositories;

import com.example.springjpa.entities.Shifts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ShiftsRepository extends JpaRepository<Shifts, UUID> {
    @Query(value = "SELECT * FROM Shifts s " +
            "WHERE s.date_start = :startDate " +
            "AND s.date_end = :endDate " +
            "ORDER BY s.name ASC LIMIT 3", nativeQuery = true)
    List<Shifts> findTop3ShiftsByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
