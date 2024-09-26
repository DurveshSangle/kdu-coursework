package com.example.springjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private String msg;
    private int statusCode;
    String path;
}
