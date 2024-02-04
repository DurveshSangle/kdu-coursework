package com.kdu.smarthome.repositories;

import com.kdu.smarthome.entities.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomRepository extends JpaRepository<Rooms, UUID> {
}
