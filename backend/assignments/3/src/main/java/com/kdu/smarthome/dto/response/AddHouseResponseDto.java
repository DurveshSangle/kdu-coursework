package com.kdu.smarthome.dto.response;

import com.kdu.smarthome.entities.Houses;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddHouseResponseDto {
    String message;
    Houses house;
    int httpStatus;
}
