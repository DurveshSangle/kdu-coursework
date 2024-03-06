package com.kdu.smarthome.exceptions;

import com.kdu.smarthome.dto.request.ErrorDTO;
import com.kdu.smarthome.exceptions.customexceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(value = {OnlyAdminAllowedException.class, InvalidCredentialForDeviceException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorDTO> unauthorizedException(Exception ex, HttpServletRequest request) {
        String requestPath = new ServletWebRequest(request).getRequest().getRequestURI();
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.UNAUTHORIZED.value(), requestPath);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {NoSuchHouseException.class, NoSuchRoomException.class, NoSuchDeviceException.class,NoSuchUserException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> badRequestException(Exception ex, HttpServletRequest request) {
        String requestPath = new ServletWebRequest(request).getRequest().getRequestURI();
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), requestPath);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
