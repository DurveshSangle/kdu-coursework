package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.HouseDto;
import com.kdu.smarthome.dto.request.UserDto;
import com.kdu.smarthome.dto.response.*;
import com.kdu.smarthome.entities.Houses;
import com.kdu.smarthome.exceptions.customexceptions.NoSuchHouseException;
import com.kdu.smarthome.exceptions.customexceptions.NoSuchUserException;
import com.kdu.smarthome.exceptions.customexceptions.OnlyAdminAllowedException;
import com.kdu.smarthome.services.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/house")
public class HouseController {
    HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    /**
     * Adds a new house based on the provided HouseDto.
     *
     * @param houseDto The data to create the house.
     * @return ResponseEntity containing the response for adding a new house.
     * @throws NoSuchUserException If the associated user is not found.
     */
    @PostMapping
    public ResponseEntity<AddHouseResponseDto> addNewHouse(@RequestBody HouseDto houseDto) throws NoSuchUserException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Houses house = houseService.addHouse(houseDto,username);
        AddHouseResponseDto addHouseResponseDto = new AddHouseResponseDto("house added",house, HttpStatus.OK.value());
        log.info("House Added");
        return ResponseEntity.ok(addHouseResponseDto);
    }

    /**
     * Adds a user to an existing house.
     *
     * @param houseId            The UUID of the house to add the user to.
     * @param userDto            The user information to be added to the house.
     * @return ResponseEntity containing the response for adding a user to a house.
     * @throws NoSuchHouseException      If the specified house is not found.
     * @throws NoSuchUserException       If the specified user is not found.
     * @throws OnlyAdminAllowedException If the requesting user is not the admin of the house.
     */
    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<String> addUserToHouse(@PathVariable String houseId, @RequestBody UserDto userDto) throws NoSuchHouseException,NoSuchUserException, OnlyAdminAllowedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String requestingUsername = authentication.getName();
        UUID houseUUID = UUID.fromString(houseId);
        houseService.addUserToHouse(houseUUID,userDto.getUsername(),requestingUsername);
        log.info("User Added to House");
        return ResponseEntity.ok("added user to house");
    }

    /**
     * Retrieves a list of all houses.
     *
     * @return ResponseEntity containing the response for retrieving a list of houses.
     */
    @GetMapping
    public ResponseEntity<HouseListResponse> getListOfHouses(){
        List<Houses> housesList = houseService.getAllHouses();
        HouseListResponse houseListResponse = new HouseListResponse("List of houses fetched",housesList.toString(),HttpStatus.OK.value());
        log.info("Fetched List of Houses");
        return ResponseEntity.ok(houseListResponse);
    }

    /**
     * Updates the address of an existing house.
     *
     * @param houseDto The data to update the house address.
     * @param houseId  The UUID of the house to update.
     * @return ResponseEntity containing the response for updating a house address.
     * @throws NoSuchHouseException If the specified house is not found.
     */
    @PutMapping
    public ResponseEntity<UpdateAddressResponse> updateHouseAddress(@RequestBody HouseDto houseDto, @RequestParam String houseId) throws NoSuchHouseException{
        Houses house = houseService.updateAddressOfHouse(houseDto.getAddress(),houseId);
        UpdateAddressResponse updateAddressResponse = new UpdateAddressResponse("updated address",house.getAddress(),HttpStatus.OK);
        log.info("Updated address of house");
        return ResponseEntity.ok(updateAddressResponse);
    }

    /**
     * Retrieves information about all devices in a specified house.
     *
     * @param houseId The UUID of the house to retrieve device information.
     * @return ResponseEntity containing the response for retrieving information about devices in a house.
     * @throws NoSuchHouseException If the specified house is not found.
     */
    @GetMapping("/{houseId}")
    public ResponseEntity<RetrieveRoomListResponse> getAllDevicesInHouse(@PathVariable String houseId) throws NoSuchHouseException{
        UUID houseUUID = UUID.fromString(houseId);
        String roomsAndDevices = houseService.getAllDevicesInHouse(houseUUID);
        RetrieveRoomListResponse retrieveRoomListResponse = new RetrieveRoomListResponse("List of rooms in house",roomsAndDevices,HttpStatus.OK);
        log.info("Fetched Devices of the House");
        return ResponseEntity.ok(retrieveRoomListResponse);
    }
}
