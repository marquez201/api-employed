package com.rmv.empleados.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rmv.empleados.dtos.EmployedDtos;
import com.rmv.empleados.services.EmployedService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("employed")
public class EmployedController {

    @Autowired
    EmployedService employedService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public ResponseEntity<List<EmployedDtos>> getAllEmployeds() {
        logger.info("***ENPOINT GET /EMPLOYED***");
        List<EmployedDtos> list = employedService.getAllEmployed();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping
    public ResponseEntity<EmployedDtos> saveEmployeds(@ Valid @RequestBody EmployedDtos employedDtos) {
        logger.info("****ENPOINT POST /EMPLOYED [DTOS: {}]****", employedDtos);
        EmployedDtos save = employedService.saveEmployed(employedDtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }


    @GetMapping("/name")
    public ResponseEntity<List<EmployedDtos>> findFirstNames(@RequestParam("firstName") String firstName) {
        logger.info("****ENPOINT GET /EMPLOYED/NAME [NAME: {}]****", firstName);
        List<EmployedDtos> listNames = employedService.findEmploydFirstName(firstName.toUpperCase());
        return ResponseEntity.status(HttpStatus.OK).body(listNames);
    }

    @GetMapping("/correo")
    public ResponseEntity<EmployedDtos> findEmail(@RequestParam("email") String email) {
        logger.info("****ENPOINT GET /EMPLOYED/CORREO [CORREO: {}]****", email);
        EmployedDtos emailEmployed = employedService.findEmployedByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(emailEmployed);
    }

    @GetMapping("/user")
    public ResponseEntity<EmployedDtos> getEmployedId(@RequestParam("id") String id) {
        logger.info("****ENPOINT GET /EMPLOYED/USER [USER: {}]****", id);
        EmployedDtos employedDtos = employedService.findEmploydId(id);
        return ResponseEntity.status(HttpStatus.OK).body(employedDtos);
    }

    @PutMapping("/update")
    public ResponseEntity<EmployedDtos> updateEmployed(@RequestBody EmployedDtos employedDtos) {
        logger.info("****ENPOINT PUT /EMPLOYED/UPDATE [DTO: {}]****", employedDtos);
        EmployedDtos update = employedService.updateEmployed(employedDtos);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEmployed(@RequestParam("id") String id) {
        logger.info("****ENPOINT DELETE /EMPLOYED/DELETE [ID: {}]****", id);
        String employedDtos = employedService.deleteEmployed(id);
        return ResponseEntity.status(HttpStatus.OK).body(employedDtos);
    }

}
