package org.example.runner;

import org.example.Logging;
import org.example.entity.Vehicle;
import org.example.exceptions.InventoryEmptyException;
import org.example.services.VehicleFactoryOneService;
import org.example.services.VehicleFactoryTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class Runner {
    @Autowired
    @Qualifier("factoryOne")
    private VehicleFactoryOneService factoryOne;

    @Autowired
    @Qualifier("factoryTwo")
    private VehicleFactoryTwoService factoryTwo;

    Logging log = new Logging();

    public VehicleFactoryOneService getFactoryOne() {
        return factoryOne;
    }
    public void setFactoryOne(VehicleFactoryOneService factoryOne) {
        this.factoryOne = factoryOne;
    }
    public VehicleFactoryTwoService getFactoryTwo() {
        return factoryTwo;
    }
    public void setFactoryTwo(VehicleFactoryTwoService factoryTwo) {
        this.factoryTwo = factoryTwo;
    }

    /**
     * This method finds the most and least expensive vehicles and prints the result to terminal
     * */
    public void run(){
        try{
            String mostExpensive = Vehicle.costliest(factoryOne.getInventory().findMostExpensiveVehicle(),factoryTwo.getInventory().findMostExpensiveVehicle()).toString();
            String leastExpensive = Vehicle.cheapest(factoryOne.getInventory().findLeastExpensiveVehicle(),factoryTwo.getInventory().findLeastExpensiveVehicle()).toString();
            log.logInfo("The most expensive vehicle is "+mostExpensive);
            log.logInfo("The least expensive vehicle is "+leastExpensive);
        } catch(InventoryEmptyException e){
            log.logError("Inventory of any of the factory is empty!!!");
        }
    }
}
