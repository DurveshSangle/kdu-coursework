package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class HouseListResponse {
    String message;
    String houses;
    int httpStatus;
}
