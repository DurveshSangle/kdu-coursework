package com.kdu.smarthome.dto.request;

import com.kdu.smarthome.entities.Houses;
import lombok.Data;

@Data
public class HouseDto {
    String address;
    String house_name;
    String newAddress;

    public Houses toHouses(){
        Houses house = new Houses();
        house.setHouse_name(house_name);
        house.setAddress(address);
        return house;
    }
}
