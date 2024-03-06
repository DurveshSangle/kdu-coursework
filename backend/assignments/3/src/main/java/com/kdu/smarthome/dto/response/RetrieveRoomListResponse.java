package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class RetrieveRoomListResponse {
    String message;
    String roomsAndDevices;
    HttpStatus httpStatus;
}
