package com.rmv.empleados.controller.advice;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.rmv.empleados.dtos.response.ApiResponse;
import com.rmv.empleados.dtos.response.Meta;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    UUID id = UUID.randomUUID();

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        Meta meta = new Meta(
            id.toString(),
            "NOT_FOUND",
            HttpStatus.NOT_FOUND.value(),
            new Date(),
            exception.getMessage()
        );
        ApiResponse<Void> response = new ApiResponse<>(null, meta);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        Meta meta = new Meta(
            id.toString(),
            "BAD_REQUEST",
            HttpStatus.BAD_REQUEST.value(),
            new Date(),
            ex.getMessage()
        );
        ApiResponse<Void> response = new ApiResponse<>(null, meta);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex, WebRequest request) {
        Meta meta = new Meta(
            id.toString(),
            "INTERNAL_SERVER_ERROR",
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            new Date(),
            ex.getMessage()
        );
        ApiResponse<Void> response = new ApiResponse<>(null, meta);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
