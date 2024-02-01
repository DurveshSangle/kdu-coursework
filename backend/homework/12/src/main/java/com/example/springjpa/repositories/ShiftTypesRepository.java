package com.example.springjpa.repositories;

import com.example.springjpa.entities.ShiftTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShiftTypesRepository extends JpaRepository<ShiftTypes, UUID> {

}
