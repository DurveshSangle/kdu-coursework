package com.example.springrest.repository;

import com.example.springrest.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Data
@AllArgsConstructor
@Repository
public class Inventory {
    List<Vehicle> vehicles;
    public void addVehiclesToList(Vehicle vehicle){
        vehicles.add(vehicle);
    }

    /**
     * Method to find vehicle by its id
     * */
    public Vehicle getVehicleById(int vehicleId){
        return vehicles.stream()
                .filter(vehicle -> vehicle.getVehicleId() == vehicleId)
                .findFirst()
                .orElse(null);
    }

    /**
     * Method to search vehicle by its name
     * */
    public Vehicle searchVehicleByName(String name){
        return vehicles.stream()
                .filter(vehicle -> vehicle.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Method to update vehicle object of vehicleId as id with corresponding updated vehicle object
     * */
    public boolean updateVehicleById(int id,Vehicle updatedVehicle){
        Vehicle vehicle = getVehicleById(id);
        if(vehicle==null) return false;
        vehicle.setVehicleId(updatedVehicle.getVehicleId());
        vehicle.setName(updatedVehicle.getName());
        vehicle.setPrice(updatedVehicle.getPrice());
        return true;
    }

    /**
     * Method to delete vehicle object with vehicleId as id
     * */
    public boolean deleteVehicleById(int id) {
        return vehicles.removeIf(vehicle -> vehicle.getVehicleId()==id);
    }

    /** Method to find the most expensive vehicle from the inventory
     * @return most expensive vehicle object
     * */
    public Vehicle findMostExpensiveVehicle() {
        return vehicles.stream().max(Comparator.comparingInt(Vehicle::getPrice)).orElse(null);
    }

    /** Method to find the least expensive vehicle from the inventory
     * @return least expensive vehicle object
     * */
    public Vehicle findLeastExpensiveVehicle(){
        return vehicles.stream().min(Comparator.comparingInt(Vehicle::getPrice)).orElse(null);
    }
}
