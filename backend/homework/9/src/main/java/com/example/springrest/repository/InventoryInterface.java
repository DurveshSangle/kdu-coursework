package com.example.springrest.repository;

import com.example.springrest.entity.Vehicle;
import com.example.springrest.exceptions.EmptyInventoryException;
import com.example.springrest.exceptions.NoSuchVehicleIdFoundException;

public interface InventoryInterface {
    void addVehicles(Vehicle vehicle);
    Vehicle getVehicleById(int vehicleId) throws NoSuchVehicleIdFoundException;
    Vehicle searchVehicleByName(String name) throws NoSuchVehicleIdFoundException;
    void updateVehicleById(int vehicleId,Vehicle updatedVehicle) throws NoSuchVehicleIdFoundException;
    void deleteVehicleById(int vehicleId) throws NoSuchVehicleIdFoundException;
    Vehicle findMostExpensiveVehicle() throws EmptyInventoryException;
    Vehicle findLeastExpensiveVehicle() throws EmptyInventoryException;
}
