package com.example.springsecurity.exception;

import com.example.springsecurity.dto.ErrorDTO;
import com.example.springsecurity.exception.custom.NoSuchUSerException;
import com.example.springsecurity.exception.custom.UnauthorizedUserException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler implements AccessDeniedHandler {
    @ExceptionHandler(value = {UnauthorizedUserException.class})
    public ResponseEntity<ErrorDTO> unAuthorizedException(UnauthorizedUserException ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage() , HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {NoSuchUSerException.class})
    public ResponseEntity<ErrorDTO> noResourceExceptions(NoSuchUSerException ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: " + accessDeniedException.getMessage());
    }
}
