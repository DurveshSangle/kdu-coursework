package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.DeviceAddDto;
import com.kdu.smarthome.dto.request.DevicesDto;
import com.kdu.smarthome.dto.response.InventoryResponse;
import com.kdu.smarthome.dto.response.AddUserHouseResponse;
import com.kdu.smarthome.entities.Devices;
import com.kdu.smarthome.exceptions.customexceptions.InvalidCredentialForDeviceException;
import com.kdu.smarthome.exceptions.customexceptions.NoSuchDeviceException;
import com.kdu.smarthome.exceptions.customexceptions.NoSuchHouseException;
import com.kdu.smarthome.exceptions.customexceptions.NoSuchRoomException;
import com.kdu.smarthome.services.DeviceServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class DeviceController {
    DeviceServices deviceServices;

    @Autowired
    public DeviceController(DeviceServices deviceServices) {
        this.deviceServices = deviceServices;
    }

    /**
     * Retrieves a list of devices from the inventory.
     *
     * @return ResponseEntity containing the response for retrieving items from the inventory.
     */
    @GetMapping("/inventory")
    public ResponseEntity<InventoryResponse> retrieveItemFromInventory(){
        List<Devices> devicesList  = deviceServices.getDevicesFromInventory();
        InventoryResponse inventoryResponse = new InventoryResponse(devicesList.toString(), HttpStatus.OK);
        log.info("Retrieved Items from Inventory");
        return ResponseEntity.ok(inventoryResponse);
    }

    /**
     * Adds a device to the inventory.
     *
     * @param devicesDto The device information to be added to the inventory.
     * @return ResponseEntity containing the response for adding a device to the inventory.
     */
    @PostMapping("inventory")
    public ResponseEntity<AddUserHouseResponse> addDeviceToInventory(@RequestBody DevicesDto devicesDto){
        deviceServices.addDeviceToInventory(devicesDto.toDevices());
        AddUserHouseResponse addUserHouseResponse = new AddUserHouseResponse("Item added to inventory","Items are nice",HttpStatus.OK.value());
        log.info("Item added to Inventory");
        return ResponseEntity.ok(addUserHouseResponse);
    }

    /**
     * Registers a device.
     *
     * @param devicesDto The device information for registration.
     * @return ResponseEntity containing the response for registering a device.
     * @throws NoSuchDeviceException             If the specified device is not found.
     * @throws InvalidCredentialForDeviceException If the provided credentials are invalid.
     */
    @PostMapping("/device/register")
    public ResponseEntity<AddUserHouseResponse> registerDevice(@RequestBody DevicesDto devicesDto) throws NoSuchDeviceException, InvalidCredentialForDeviceException {
        deviceServices.isDeviceValid(devicesDto);
        AddUserHouseResponse addUserHouseResponse = new AddUserHouseResponse("Device Registered","Device is good",HttpStatus.OK.value());
        log.info("Device Registered !!");
        return ResponseEntity.ok(addUserHouseResponse);
    }

    /**
     * Adds a device to a house.
     *
     * @param deviceAddDto The information about the device, house, and room.
     * @return ResponseEntity containing the response for adding a device to a house.
     * @throws NoSuchHouseException   If the specified house is not found.
     * @throws NoSuchDeviceException  If the specified device is not found.
     * @throws NoSuchRoomException    If the specified room is not found.
     */
    @PostMapping("/device/add")
    public ResponseEntity<AddUserHouseResponse> addDeviceToHouse(@RequestBody DeviceAddDto deviceAddDto) throws NoSuchHouseException, NoSuchDeviceException, NoSuchRoomException {
        deviceServices.addDeviceToHouse(deviceAddDto);
        AddUserHouseResponse addUserHouseResponse = new AddUserHouseResponse("Devices added to house !!","Devices are nice",HttpStatus.OK.value());
        log.info("Added device to house of give houseId");
        return ResponseEntity.ok(addUserHouseResponse);
    }
}
