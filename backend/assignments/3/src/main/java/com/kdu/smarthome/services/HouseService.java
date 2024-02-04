package com.kdu.smarthome.services;

import com.kdu.smarthome.dto.request.HouseDto;
import com.kdu.smarthome.entities.Houses;
import com.kdu.smarthome.entities.Users;
import com.kdu.smarthome.exceptions.customexceptions.NoSuchHouseException;
import com.kdu.smarthome.exceptions.customexceptions.NoSuchUserException;
import com.kdu.smarthome.exceptions.customexceptions.OnlyAdminAllowedException;
import com.kdu.smarthome.repositories.HouseRepository;
import com.kdu.smarthome.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class HouseService {
    HouseRepository houseRepository;
    UsersRepository usersRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository, UsersRepository usersRepository) {
        this.houseRepository = houseRepository;
        this.usersRepository = usersRepository;
    }

    /**
     * Adds a new house based on the provided HouseDto and associates it with the given username.
     *
     * @param houseDto   The data to create the house.
     * @param username   The username of the user associated with the house.
     * @return The created house.
     * @throws NoSuchUserException If the associated user is not found.
     */
    public Houses addHouse(HouseDto houseDto,String username) throws NoSuchUserException{
        Houses houses = houseDto.toHouses();
        Users user = usersRepository.findByUsername(username);
        if(user==null) {
            log.error("User should register first!!");
            throw new NoSuchUserException("Invalid username, register user first");
        }
        houses.setAdminUser(user);
        houses.setUsersList(Arrays.asList(user));
        houseRepository.save(houses);
        return houses;
    }

    /**
     * Adds a user to an existing house. Requires admin privileges.
     *
     * @param houseId            The UUID of the house to add the user to.
     * @param username           The username of the user to be added.
     * @param requestingUsername The username of the user making the request (admin).
     * @throws NoSuchHouseException      If the specified house is not found.
     * @throws NoSuchUserException       If the specified user is not found.
     * @throws OnlyAdminAllowedException If the requesting user is not the admin of the house.
     */
    public void addUserToHouse(UUID houseId, String username, String requestingUsername) throws NoSuchHouseException,NoSuchUserException, OnlyAdminAllowedException{
        Optional<Houses> optionalHouses = houseRepository.findById(houseId);
        if(optionalHouses.isEmpty()) {
            log.error("Invalid HouseId");
            throw new NoSuchHouseException("Invalid House Id !!!");
        }
        Houses house = optionalHouses.get();
        if(!house.getAdminUser().getUsername().equals(requestingUsername)){
            log.error("User is not a admin");
            throw new OnlyAdminAllowedException("Action allowed to admin of house only!!");
        }
        Users user = usersRepository.findByUsername(username);
        if(user==null) {
            log.error("User is not a registered");
            throw new NoSuchUserException("Invalid username, register user first");
        }
        List<Users> userList = house.getUsersList();
        userList.add(user);
        house.setUsersList(userList);
        houseRepository.save(house);
    }

    /**
     * Updates the address of an existing house.
     *
     * @param newAddress The new address to set.
     * @param houseId    The UUID of the house to update.
     * @return The updated house.
     * @throws NoSuchHouseException If the specified house is not found.
     */
    public Houses updateAddressOfHouse(String newAddress, String houseId) throws NoSuchHouseException{
        UUID houseUUID;
        try{
            houseUUID = UUID.fromString(houseId);
        } catch(IllegalArgumentException e){
            log.error("HouseId is not a UUID");
            throw new NoSuchHouseException("Invalid houseId, not in UUID format");
        }
        Optional<Houses> optionalHouses = houseRepository.findById(houseUUID);
        if(optionalHouses.isEmpty()) {
            log.error("HouseId is invalid");
            throw new NoSuchHouseException("Invalid House Id!!!");
        }
        Houses house = optionalHouses.get();
        house.setAddress(newAddress);
        houseRepository.save(house);
        return house;
    }

    /**
     * Retrieves information about all devices in a specified house.
     *
     * @param houseId The UUID of the house to retrieve device information.
     * @return A string containing house information and the list of devices in its rooms.
     * @throws NoSuchHouseException If the specified house is not found.
     */
    public String getAllDevicesInHouse(UUID houseId)throws NoSuchHouseException{
        Optional<Houses> optionalHouses = houseRepository.findById(houseId);
        if(optionalHouses.isEmpty()) {
            log.error("Invalid House Id!!");
            throw new NoSuchHouseException("Invalid House Id!!!");
        }
        Houses house = optionalHouses.get();
        String houseString = house.getHouse_name()+" "+house.getAddress()+" "+house.getId();
        return houseString + house.getRoomsList().toString();
    }

    /**
     * Retrieves a list of all houses.
     *
     * @return List of all houses.
     */
    public List<Houses> getAllHouses(){
        return houseRepository.findAll();
    }
}
