package com.example.springjdbc.exceptions;

import com.example.springjdbc.dto.ErrorDTO;
import com.example.springjdbc.exceptions.custom.FailedTransactionRollBackException;
import com.example.springjdbc.exceptions.custom.NoSuchIdExistException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NoSuchIdExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> badRequestException(Exception ex, HttpServletRequest request) {
        String requestPath = new ServletWebRequest(request).getRequest().getRequestURI();
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), requestPath);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = FailedTransactionRollBackException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDTO> failedtransactionException(Exception ex, HttpServletRequest request) {
        String requestPath = new ServletWebRequest(request).getRequest().getRequestURI();
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), requestPath);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
