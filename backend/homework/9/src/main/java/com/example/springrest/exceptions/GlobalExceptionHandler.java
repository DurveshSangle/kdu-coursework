package com.example.springrest.exceptions;

import com.example.springrest.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {NoSuchVehicleIdFoundException.class, EmptyInventoryException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDTO> notFoundException(Exception ex, HttpServletRequest request) {
        String requestPath = new ServletWebRequest(request).getRequest().getRequestURI();

        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value(), requestPath);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}