package com.example.springrest.entity;

import com.example.springrest.dto.VehicleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vehicle {
    int vehicleId;
    String name;
    int price;

    public static Vehicle convertDTOVehicleToVehicle(VehicleDTO vehicleDTO){
        return new Vehicle(vehicleDTO.getVehicleId(), vehicleDTO.getName(),vehicleDTO.getPrice());
    }

    public String toString(){
        return "\n\t\tvehicleId:- "+vehicleId+
                "\n\t\tName:- "+name+
                "\n\t\tPrice: "+price;
    }
}
