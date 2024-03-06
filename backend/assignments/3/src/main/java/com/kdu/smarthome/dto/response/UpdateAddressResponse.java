package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class UpdateAddressResponse {
    String message;
    String houses;
    HttpStatus httpStatus;
}
