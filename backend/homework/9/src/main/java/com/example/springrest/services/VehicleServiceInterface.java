package com.example.springrest.services;

import com.example.springrest.entity.Vehicle;
import com.example.springrest.exceptions.EmptyInventoryException;
import com.example.springrest.exceptions.NoSuchVehicleIdFoundException;

public interface VehicleServiceInterface {
    void addVehicleToInventory(Vehicle v);
    void updateVehicleToInventory(int vehicleId,Vehicle updatedVehicle) throws NoSuchVehicleIdFoundException;;
    Vehicle retrieveVehicleFromInventory(int vehicleId) throws NoSuchVehicleIdFoundException;
    void deleteVehicleFromInventory(int vehicleId) throws NoSuchVehicleIdFoundException;
    Vehicle searchVehicleFromInventory(String vehicleName) throws NoSuchVehicleIdFoundException;
    Vehicle mostExpVehicleInInventory() throws EmptyInventoryException;
    Vehicle leastExpVehicleInInventory() throws EmptyInventoryException;
}
