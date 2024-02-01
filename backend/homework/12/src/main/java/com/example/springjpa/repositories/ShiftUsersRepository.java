package com.example.springjpa.repositories;

import com.example.springjpa.entities.ShiftUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShiftUsersRepository extends JpaRepository<ShiftUsers, UUID> {
}
