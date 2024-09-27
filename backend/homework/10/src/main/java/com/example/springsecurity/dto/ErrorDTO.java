package com.example.springsecurity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {
    String message;
    int statusCode;
}
