package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class AddRoomResponse {
    @AllArgsConstructor
    @Data
    public static class RoomMap{
        private String id;
    }
    String message;
    RoomMap room;
    HttpStatus httpStatus;
}
