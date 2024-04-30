package com.rmv.empleados.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rmv.empleados.dtos.EmployedDtos;
import com.rmv.empleados.entitys.EmployedEntity;
import com.rmv.empleados.mappers.EmployedMapper;
import com.rmv.empleados.repository.EmployedRepository;

@Service
public class EmployedService {
    @Autowired
    EmployedMapper employedMapper;

    @Autowired
    EmployedRepository employedRepository;

    @Transactional
    public List<EmployedDtos> getAllEmployed() {
        List<EmployedEntity> employedEntities = employedRepository.findAll();
        return employedEntities.stream().
            map(employedMapper::toDtos).
            collect(Collectors.toList()
        );
    }

    @Transactional
    public EmployedDtos saveEmployed(EmployedDtos employedDtos) {
        EmployedEntity employedEntity = employedMapper.toEntity(employedDtos);
        employedEntity = employedRepository.save(employedEntity);
        return employedMapper.toDtos(employedEntity);
    }
}