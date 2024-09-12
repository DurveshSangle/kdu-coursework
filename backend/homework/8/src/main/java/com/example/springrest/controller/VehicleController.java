package com.example.springrest.controller;

import com.example.springrest.dto.VehicleDTO;
import com.example.springrest.entity.Vehicle;
import com.example.springrest.services.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleController {
    private VehicleService vehicleService;
    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    /**
     * REST API to insert new vehicle into inventory
     *
     * @param vehicleDTO vehicle object to be added
     * @return response entity with ok status code.
     * */
    @PostMapping("/create")
    public ResponseEntity<String> addVehicle(@RequestBody @Valid VehicleDTO vehicleDTO){
        Vehicle vehicle = Vehicle.convertDTOVehicleToVehicle(vehicleDTO);
        vehicleService.addVehicleToInventory(vehicle);
        return ResponseEntity.ok("Vehicle added successfully");
    }

    /**
     * REST API to retrieve vehicle from inventory
     *
     * @param vehicleId id of vehicle to be retrieved
     * @return vehicle object of that vehicleId.
     * */
    @GetMapping("/retrieve/{vehicleId}")
    public VehicleDTO getVehicle(@PathVariable int vehicleId){
        Vehicle vehicle = vehicleService.getInventory().getVehicleById(vehicleId);
        return VehicleDTO.convertDTOVehicleToVehicle(vehicle);
    }

    /**
     * REST API to search vehicle from inventory
     *
     * @param name name of vehicle to be searched
     * @return vehicle object of that vehicle name.
     * */
    @GetMapping("/search")
    public VehicleDTO searchVehicle(@RequestParam String name){
        Vehicle vehicle = vehicleService.getInventory().searchVehicleByName(name);
        return VehicleDTO.convertDTOVehicleToVehicle(vehicle);
    }

    /**
     * REST API to update vehicle into inventory
     *
     * @param vehicleId id of vehicle to be updated
     * @param vehicleDTO updated vehicle object
     * @return response entity with ok status code.
     * */
    @PutMapping("/update/{vehicleId}")
    public ResponseEntity<String> updateVehicle(@PathVariable int vehicleId, @RequestBody @Valid VehicleDTO vehicleDTO){
        Vehicle vehicle = Vehicle.convertDTOVehicleToVehicle(vehicleDTO);
        if(vehicleService.getInventory().updateVehicleById(vehicleId,vehicle)) return ResponseEntity.ok("Vehicle updated successfully");
        return ResponseEntity.ofNullable("NO such vehicle exist !!");
    }

    /**
     * REST API to delete vehicle from inventory
     *
     * @param vehicleId id of vehicle to be deleted
     * @return response entity with ok status code.
     * */
    @DeleteMapping("/delete/{vehicleId}")
    public ResponseEntity<String> deleteVehicle(@PathVariable int vehicleId){
        if(vehicleService.getInventory().deleteVehicleById(vehicleId)) return ResponseEntity.ok("Vehicle deleted successfully");
        return ResponseEntity.ofNullable("NO such vehicle exist !!");
    }

    /**
     * REST API to find most expensive vehicle from inventory
     *
     * @return expensive vehicle object
     * */
    @GetMapping("/expensive")
    public VehicleDTO mostExpensiveVehicle(){
        Vehicle vehicle = vehicleService.getInventory().findMostExpensiveVehicle();
        return VehicleDTO.convertDTOVehicleToVehicle(vehicle);
    }

    /**
     * REST API to find the least expensive vehicle from inventory
     *
     * @return cheap vehicle object
     * */
    @GetMapping("/cheap")
    public VehicleDTO leastExpensiveVehicle(){
        Vehicle vehicle = vehicleService.getInventory().findLeastExpensiveVehicle();
        return VehicleDTO.convertDTOVehicleToVehicle(vehicle);
    }
}
