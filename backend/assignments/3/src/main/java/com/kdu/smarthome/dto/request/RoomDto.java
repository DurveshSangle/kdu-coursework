package com.kdu.smarthome.dto.request;

import com.kdu.smarthome.entities.Rooms;
import lombok.Data;

@Data
public class RoomDto {
    String room_name;

    public Rooms toRooms(){
        Rooms room = new Rooms();
        room.setRoom_name(room_name);
        return room;
    }
}
