package com.kdu.smarthome.services;

import com.kdu.smarthome.entities.Houses;
import com.kdu.smarthome.entities.Rooms;
import com.kdu.smarthome.exceptions.customexceptions.NoSuchHouseException;
import com.kdu.smarthome.repositories.HouseRepository;
import com.kdu.smarthome.repositories.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class RoomsServices {
    RoomRepository roomRepository;
    HouseRepository houseRepository;

    @Autowired
    public RoomsServices(RoomRepository roomRepository, HouseRepository houseRepository) {
        this.roomRepository = roomRepository;
        this.houseRepository = houseRepository;
    }

    /**
     * Adds a new room to an existing house.
     *
     * @param houseId The UUID of the house to which the room should be added.
     * @param room    The room to be added.
     * @return The added room.
     * @throws NoSuchHouseException If the specified house is not found.
     */
    public Rooms addRoomToHouse(String houseId, Rooms room) throws NoSuchHouseException{
        UUID houseUUID;
        try{
            houseUUID = UUID.fromString(houseId);
        } catch(IllegalArgumentException e){
            log.error("HouseId is not a UUID");
            throw new NoSuchHouseException("Invalid HouseId, not in UUID format !!");
        }
        Optional<Houses> optionalHouses = houseRepository.findById(houseUUID);
        if(optionalHouses.isEmpty()) {
            log.error("HouseId is invalid");
            throw new NoSuchHouseException("Invalid House Id!!!");
        }
        Houses house = optionalHouses.get();
        List<Rooms> roomsList = house.getRoomsList();
        roomsList.add(room);
        house.setRoomsList(roomsList);
        houseRepository.save(house);
        roomRepository.save(room);
        return room;
    }
}
