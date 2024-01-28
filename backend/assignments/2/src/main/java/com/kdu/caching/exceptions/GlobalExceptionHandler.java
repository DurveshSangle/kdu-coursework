package com.kdu.caching.exceptions;

import com.kdu.caching.dto.ErrorDTO;
import com.kdu.caching.exceptions.customexceptions.InvalidAddressException;
import com.kdu.caching.exceptions.customexceptions.InvalidLatitudeLongitudeException;
import com.kdu.caching.exceptions.customexceptions.UnableToDeserializeJSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception Handler for bad requests to server
     * */
    @ExceptionHandler(value = {InvalidAddressException.class, InvalidLatitudeLongitudeException.class, UnableToDeserializeJSONException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> badRequest(Exception ex, HttpServletRequest request) {
        String requestPath = new ServletWebRequest(request).getRequest().getRequestURI();
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), requestPath);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
