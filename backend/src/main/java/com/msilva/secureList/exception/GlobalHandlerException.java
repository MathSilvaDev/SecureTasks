package com.msilva.secureList.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    private ResponseEntity<ApiError> toResponseEntity(HttpStatus httpStatus, String message){
        return ResponseEntity
                .status(httpStatus)
                .body(new ApiError(httpStatus, message));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiError> illegalStateException(IllegalStateException ex){
        return toResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiError> usernameNotFoundException(UsernameNotFoundException ex){
        return toResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
