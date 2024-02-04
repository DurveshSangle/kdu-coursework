package com.kdu.smarthome.repositories;

import com.kdu.smarthome.entities.Devices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Inventory extends JpaRepository<Devices, String> {
}
