package com.rmv.empleados.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmv.empleados.dtos.EmployedDtos;
import com.rmv.empleados.services.EmployedService;

@RestController
@RequestMapping("employed")
public class EmployedController {

    @Autowired
    EmployedService employedService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public ResponseEntity<List<EmployedDtos>> getAllEmployeds() {
        List<EmployedDtos> list = employedService.getAllEmployed();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping
    public ResponseEntity<EmployedDtos> saveEmployeds(@RequestBody EmployedDtos employedDtos) {
        EmployedDtos save = employedService.saveEmployed(employedDtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }


    @GetMapping("/name/{firstName}")
    public ResponseEntity<List<EmployedDtos>> findFirstNames(@PathVariable("firstName") String firstName) {
        List<EmployedDtos> listNames = employedService.findEmploydFirstName(firstName);
        return ResponseEntity.status(HttpStatus.OK).body(listNames);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<EmployedDtos> getEmployedId(@PathVariable("id") String id) {
        EmployedDtos employedDtos = employedService.findEmploydId(id);
        return ResponseEntity.status(HttpStatus.OK).body(employedDtos);
    }

    @PutMapping("/update")
    public ResponseEntity<EmployedDtos> updateEmployed(@RequestBody EmployedDtos employedDtos) {
        EmployedDtos update = employedService.updateEmployed(employedDtos);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

}
