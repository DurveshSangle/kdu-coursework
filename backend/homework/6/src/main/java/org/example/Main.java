package org.example;

import org.example.config.ScanConfig;
import org.example.exceptions.VehicleListEmptyException;
import org.example.services.VehicleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        Logging log = new Logging();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScanConfig.class);

        VehicleService vehicleService = context.getBean(VehicleService.class);

        try{
            log.logInfo("\nExpensive Vehicle is: "+vehicleService.findMostExpensiveVehicle());
        } catch(VehicleListEmptyException e){
            log.logError("There are no vehicles in the list. Try adding some vehicles to it.");
            e.printStackTrace();
        }
    }
}