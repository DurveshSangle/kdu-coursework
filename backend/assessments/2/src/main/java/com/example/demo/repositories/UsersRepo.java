package com.example.demo.repositories;

import com.example.demo.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsersRepo extends JpaRepository<Users, UUID> {
    Users findByEmail(String email);
}
