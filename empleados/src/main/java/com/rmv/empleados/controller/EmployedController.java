package com.rmv.empleados.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rmv.empleados.dtos.EmployedDtos;
import com.rmv.empleados.dtos.response.ApiResponse;
import com.rmv.empleados.dtos.response.Meta;
import com.rmv.empleados.services.EmployedService;

@RestController
@RequestMapping("employed")
public class EmployedController {
    
    @Autowired
    EmployedService employedService;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    UUID id = UUID.randomUUID();
    private final Meta meta = new Meta(
        id.toString(), 
        "OK", 
        HttpStatus.OK.value(), 
        new Date()
    );
    private final Meta metaFailure = new Meta(
        id.toString(), 
        HttpStatus.INTERNAL_SERVER_ERROR.name(),
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        new Date()
    );


    @GetMapping("all")
    public ResponseEntity<ApiResponse<List<EmployedDtos>>> getEmployeds() {
        try {
            
            List<EmployedDtos> getAllEmployeds = employedService.getAllEmployed();

            ApiResponse<List<EmployedDtos>> response = new ApiResponse<>(getAllEmployeds, meta);

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            logger.error(e.getMessage());

            ApiResponse<List<EmployedDtos>> responseError = new ApiResponse<>(null, metaFailure);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);

        }

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<EmployedDtos>> save(@RequestBody EmployedDtos employedDtos) {
        try {
            EmployedDtos savedEmployed = employedService.saveEmployed(employedDtos);
            
            ApiResponse<EmployedDtos> response = new ApiResponse<>(savedEmployed, meta);

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            logger.error(e.getMessage());

            ApiResponse<EmployedDtos> responseError = new ApiResponse<>(null, meta);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);

        }
    }
}
