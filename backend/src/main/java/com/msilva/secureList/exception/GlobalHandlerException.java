package com.msilva.secureList.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
