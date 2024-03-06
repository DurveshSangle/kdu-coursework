package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AddUserHouseResponse {
    String message;
    String object;
    int httpStatus;
}
