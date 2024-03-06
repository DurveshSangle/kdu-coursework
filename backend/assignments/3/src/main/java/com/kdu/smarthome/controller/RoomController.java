package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.RoomDto;
import com.kdu.smarthome.dto.response.AddRoomResponse;
import com.kdu.smarthome.entities.Rooms;
import com.kdu.smarthome.exceptions.customexceptions.NoSuchHouseException;
import com.kdu.smarthome.services.RoomsServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
@Slf4j
public class RoomController {

    RoomsServices roomsServices;

    @Autowired
    public RoomController(RoomsServices roomsServices) {
        this.roomsServices = roomsServices;
    }

    /**
     * Adds a new room to an existing house.
     *
     * @param houseId The UUID of the house to which the room should be added.
     * @param roomDto The data to create the room.
     * @return ResponseEntity containing the response for adding a new room.
     * @throws NoSuchHouseException If the specified house is not found.
     */
    @PostMapping
    public ResponseEntity<AddRoomResponse> addRoomToHouse(@RequestParam String houseId, @RequestBody RoomDto roomDto) throws NoSuchHouseException {
        Rooms room = roomsServices.addRoomToHouse(houseId,roomDto.toRooms());
        AddRoomResponse addRoomResponse = new AddRoomResponse("Room added",new AddRoomResponse.RoomMap(room.getId().toString()), HttpStatus.OK);
        log.info("Added Room to House");
        return ResponseEntity.ok(addRoomResponse);
    }
}
