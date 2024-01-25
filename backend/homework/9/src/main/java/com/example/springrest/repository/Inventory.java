package com.example.springrest.repository;

import com.example.springrest.entity.Vehicle;
import com.example.springrest.exceptions.EmptyInventoryException;
import com.example.springrest.exceptions.NoSuchVehicleIdFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Data
@AllArgsConstructor
@Repository
@Slf4j
public class Inventory implements InventoryInterface{
    List<Vehicle> vehicles;
    public void addVehicles(Vehicle vehicle){
        vehicles.add(vehicle);
        log.info("vehicle added successfully");
    }

    /**
     * Method to find vehicle by its id
     * */
    public Vehicle getVehicleById(int vehicleId) throws NoSuchVehicleIdFoundException{
        Vehicle vehicle = vehicles.stream()
                .filter(v -> v.getVehicleId() == vehicleId)
                .findFirst()
                .orElse(null);
        if(vehicle==null) throw new NoSuchVehicleIdFoundException("NOT FOUND Vehicle Id '"+vehicleId);
        return vehicle;
    }

    /**
     * Method to search vehicle by its name
     * */
    public Vehicle searchVehicleByName(String name) throws NoSuchVehicleIdFoundException{
        Vehicle vehicle =  vehicles.stream()
                .filter(v -> v.getName().equals(name))
                .findFirst()
                .orElse(null);
        if(vehicle==null) throw new NoSuchVehicleIdFoundException("NOT FOUND Vehicle with name '"+name);
        return vehicle;
    }

    /**
     * Method to update vehicle object of vehicleId as id with corresponding updated vehicle object
     * */
    public void updateVehicleById(int vehicleId,Vehicle updatedVehicle) throws NoSuchVehicleIdFoundException{
        Vehicle vehicle = getVehicleById(vehicleId);
        if(vehicle==null) throw new NoSuchVehicleIdFoundException("NOT FOUND Vehicle Id '"+vehicleId);
        vehicle.setVehicleId(updatedVehicle.getVehicleId());
        vehicle.setName(updatedVehicle.getName());
        vehicle.setPrice(updatedVehicle.getPrice());
        log.info("vehicle updated successfully");
    }

    /**
     * Method to delete vehicle object with vehicleId as id
     * */
    public void deleteVehicleById(int vehicleId) throws NoSuchVehicleIdFoundException{
        if(!vehicles.removeIf(vehicle -> vehicle.getVehicleId()==vehicleId)) throw new NoSuchVehicleIdFoundException("Vehicle Id '"+vehicleId+"' does not exist!!");
        log.info("vehicle deleted successfully");
    }

    /** Method to find the most expensive vehicle from the inventory
     * @return most expensive vehicle object
     * */
    public Vehicle findMostExpensiveVehicle() throws EmptyInventoryException{
        Vehicle vehicle = vehicles.stream().max(Comparator.comparingInt(Vehicle::getPrice)).orElse(null);
        if(vehicle==null) throw new EmptyInventoryException("Inventory of vehicles is empty!!");
        return vehicle;
    }

    /** Method to find the least expensive vehicle from the inventory
     * @return least expensive vehicle object
     * */
    public Vehicle findLeastExpensiveVehicle() throws EmptyInventoryException {
        Vehicle vehicle = vehicles.stream().min(Comparator.comparingInt(Vehicle::getPrice)).orElse(null);
        if(vehicle==null) throw new EmptyInventoryException("Inventory of vehicles is empty!!");
        return vehicle;
    }
}
