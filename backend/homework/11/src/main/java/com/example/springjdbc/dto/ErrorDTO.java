package com.example.springjdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private String msg;
    private int statusCode;
    String path;
}
