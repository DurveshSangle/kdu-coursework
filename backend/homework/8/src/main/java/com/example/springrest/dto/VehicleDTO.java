package com.example.springrest.dto;


import com.example.springrest.entity.Vehicle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleDTO {
    @Positive
    int vehicleId;
    @NotBlank
    String name;
    @Positive
    int price;

    public static VehicleDTO convertDTOVehicleToVehicle(Vehicle vehicle){
        return new VehicleDTO(vehicle.getVehicleId(), vehicle.getName(),vehicle.getPrice());
    }
}
