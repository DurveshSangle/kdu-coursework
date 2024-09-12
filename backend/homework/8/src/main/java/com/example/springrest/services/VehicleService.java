package com.example.springrest.services;

import com.example.springrest.entity.Vehicle;
import com.example.springrest.repository.Inventory;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class VehicleService {
    private Inventory inventory;

    @Autowired
    protected VehicleService(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addVehicleToInventory(Vehicle vehicle){
        inventory.addVehiclesToList(vehicle);
    }
}
