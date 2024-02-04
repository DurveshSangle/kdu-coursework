package com.kdu.smarthome.services;

import com.kdu.smarthome.dto.request.DeviceAddDto;
import com.kdu.smarthome.dto.request.DevicesDto;
import com.kdu.smarthome.entities.Devices;
import com.kdu.smarthome.entities.Houses;
import com.kdu.smarthome.entities.Rooms;
import com.kdu.smarthome.exceptions.customexceptions.InvalidCredentialForDeviceException;
import com.kdu.smarthome.exceptions.customexceptions.NoSuchDeviceException;
import com.kdu.smarthome.exceptions.customexceptions.NoSuchHouseException;
import com.kdu.smarthome.exceptions.customexceptions.NoSuchRoomException;
import com.kdu.smarthome.repositories.HouseRepository;
import com.kdu.smarthome.repositories.Inventory;
import com.kdu.smarthome.repositories.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class DeviceServices {
    Inventory inventory;
    HouseRepository houseRepository;
    RoomRepository roomRepository;


    @Autowired
    public DeviceServices(Inventory inventory, HouseRepository houseRepository, RoomRepository roomRepository) {
        this.inventory = inventory;
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
    }

    /**
     * Retrieves a list of all devices from the inventory.
     *
     * @return List of Devices in the inventory.
     */
    public List<Devices> getDevicesFromInventory(){
        return inventory.findAll();
    }

    /**
     * Adds a device to the inventory.
     *
     * @param device The device to be added.
     */
    public void addDeviceToInventory(Devices device){
        inventory.save(device);
    }

    /**
     * Validates the credentials for a given device.
     *
     * @param devicesDto The device information for validation.
     * @throws NoSuchDeviceException          If the device is not found in the inventory.
     * @throws InvalidCredentialForDeviceException If the provided credentials are invalid.
     */
    public void isDeviceValid(DevicesDto devicesDto) throws NoSuchDeviceException, InvalidCredentialForDeviceException{
        Optional<Devices> optionalDevices = inventory.findById(devicesDto.getKickston_id());
        boolean isRegistered = true;
        if(optionalDevices.isEmpty()) {
            log.error("Invalid Kickston Id!!");
            throw new NoSuchDeviceException("Invalid KickstonId !!");
        }
        Devices device = optionalDevices.get();
        if(!device.getDevice_password().equals(devicesDto.getDevice_password())) {
            log.error("Invalid Device Password!!");
            throw new InvalidCredentialForDeviceException("Invalid Password !!");
        }
        if(!device.getDevice_username().equals(devicesDto.getDevice_username())) {
            log.error("Invalid Device Username!!");
            throw new NoSuchDeviceException("Invalid Username !!");
        }
        device.setRegistered(isRegistered);
        inventory.save(device);
    }

    /**
     * Adds a device to a specific house and room.
     *
     * @param deviceAddDto The information about the device, house, and room.
     * @throws NoSuchHouseException    If the specified house is not found.
     * @throws NoSuchDeviceException   If the specified device is not found.
     * @throws NoSuchRoomException     If the specified room is not found.
     */
    public void addDeviceToHouse(DeviceAddDto deviceAddDto) throws NoSuchHouseException,NoSuchDeviceException,NoSuchRoomException{
        String kickstoneId = deviceAddDto.getKickstonId();
        UUID houseUUID;
        try{
            houseUUID = UUID.fromString(deviceAddDto.getHouseId());
        } catch(IllegalArgumentException e){
            log.error("HouseId should be a UUID");
            throw new NoSuchHouseException("HouseId is not of UUID format");
        }
        UUID roomId;
        try{
            roomId = UUID.fromString(deviceAddDto.getRoomId());
        } catch(IllegalArgumentException e){
            log.error("RoomId should be a UUID");
            throw new NoSuchRoomException("RoomId is not of UUID format");
        }
        Optional<Houses> optionalHouses = houseRepository.findById(houseUUID);
        if(optionalHouses.isEmpty()) {
            log.error("HouseId is invalid");
            throw new NoSuchHouseException("Invalid House Id!!!");
        }
        Optional<Devices> optionalDevices = inventory.findById(kickstoneId);
        if(optionalDevices.isEmpty()){
            log.error("KickstonId is invalid");
            throw new NoSuchDeviceException("Invalid KickstonId !!");
        }
        Devices devices = optionalDevices.get();
        if(!devices.isRegistered()) {
            log.error("The Device should be registered before adding to house");
            throw new NoSuchDeviceException("Device Not Registered !!");
        }
        Optional<Rooms> optionalRooms = roomRepository.findById(roomId);
        if(optionalRooms.isEmpty()){
            log.error("RoomID is invalid!!");
            throw new NoSuchRoomException("Invalid Rooms Id !!");
        }
        Rooms room = optionalRooms.get();
        List<Devices> devicesList = room.getDevicesList();
        devicesList.add(devices);
        room.setDevicesList(devicesList);
        roomRepository.save(room);
    }
}
