package com.kdu.smarthome.repositories;

import com.kdu.smarthome.entities.Houses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface HouseRepository extends JpaRepository<Houses, UUID> {
}
