package org.example.repositories;

import org.example.entity.Vehicle;
import org.example.exceptions.InventoryEmptyException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Scope("prototype")
public class Inventory {
    List<Vehicle> vehicles;

    public Inventory(){
        vehicles = new ArrayList<>();
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void addVehiclesToList(Vehicle vehicle){
        vehicles.add(vehicle);
    }

    /** Method to find the most expensive vehicle from the inventory
     * @return most expensive vehicle object
     * */
    public Vehicle findMostExpensiveVehicle() throws InventoryEmptyException {
        Optional<Vehicle> expensiveVehicle = vehicles.stream()
                .max(Comparator.comparingInt(Vehicle::getPrice));
        if(expensiveVehicle.isEmpty()){
            throw new InventoryEmptyException("List of Vehicles is Empty !!!");
        }
        else return expensiveVehicle.get();
    }

    /** Method to find the least expensive vehicle from the inventory
     * @return least expensive vehicle object
     * */
    public Vehicle findLeastExpensiveVehicle() throws InventoryEmptyException {
        Optional<Vehicle> expensiveVehicle = vehicles.stream()
                .min(Comparator.comparingInt(Vehicle::getPrice));
        if(expensiveVehicle.isEmpty()){
            throw new InventoryEmptyException("List of Vehicles is Empty !!!");
        }
        else return expensiveVehicle.get();
    }
}
