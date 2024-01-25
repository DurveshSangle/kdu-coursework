package com.example.springrest.dto;


import com.example.springrest.entity.Vehicle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleDTO {
    @NotNull(message = "VehicleId is required")
    @Positive
    private Integer vehicleId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    @Positive
    private Integer price;

    public static VehicleDTO convertDTOVehicleToVehicle(Vehicle vehicle){
        return new VehicleDTO(vehicle.getVehicleId(), vehicle.getName(),vehicle.getPrice());
    }
}
