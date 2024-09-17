package com.example.springrest.services;

import com.example.springrest.entity.Vehicle;
import com.example.springrest.exceptions.ArgumentInvalidException;
import com.example.springrest.exceptions.EmptyInventoryException;
import com.example.springrest.exceptions.NoSuchVehicleIdFoundException;
import com.example.springrest.repository.Inventory;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class VehicleService implements VehicleServiceInterface{
    private Inventory inventory;
    @Autowired
    public VehicleService(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addVehicleToInventory(Vehicle vehicle){
        inventory.addVehicles(vehicle);
    }

    public void updateVehicleToInventory(int vehicleId,Vehicle updatedVehicle) throws NoSuchVehicleIdFoundException{
        inventory.updateVehicleById(vehicleId, updatedVehicle);
    }
    public Vehicle retrieveVehicleFromInventory(int vehicleId) throws NoSuchVehicleIdFoundException {
        return inventory.getVehicleById(vehicleId);
    }
    public void deleteVehicleFromInventory (int vehicleId) throws NoSuchVehicleIdFoundException{
        inventory.deleteVehicleById(vehicleId);
    }
    public Vehicle searchVehicleFromInventory(String vehicleName) throws NoSuchVehicleIdFoundException{
        return inventory.searchVehicleByName(vehicleName);
    }
    public Vehicle mostExpVehicleInInventory() throws EmptyInventoryException {
        return inventory.findMostExpensiveVehicle();
    }
    public Vehicle leastExpVehicleInInventory() throws EmptyInventoryException {
        return inventory.findLeastExpensiveVehicle();
    }
    public int isInteger(String param) throws ArgumentInvalidException {
        if(param.matches("//d+")) return Integer.parseInt(param);
        throw new ArgumentInvalidException("Parameter is invalid");
    }

}
