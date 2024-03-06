package com.kdu.smarthome.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private String msg;
    private int statusCode;
    String path;
}
