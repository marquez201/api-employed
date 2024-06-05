package com.rmv.empleados.services;

import java.util.List;
import java.util.Optional;
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

    @Transactional(readOnly = true)
    public List<EmployedDtos> getAllEmployed() {
       return mapEmployedEntitiesToDtos(employedRepository.findAll());
    }

    @Transactional
    public EmployedDtos saveEmployed(EmployedDtos employedDtos) {
        EmployedEntity employedEntity = employedMapper.toEntity(employedDtos);
        employedEntity = employedRepository.save(employedEntity);
        return employedMapper.toDtos(employedEntity);
    }

    @Transactional(readOnly = true)
    public List<EmployedDtos> findEmploydFirstName(String firstName) {
        return mapEmployedEntitiesToDtos(employedRepository.findByFirstName(firstName));
    }

    @Transactional(readOnly = true)
    public Optional<EmployedEntity> findEmploydId(String id) {
        return employedRepository.findById(id);
    }

    private List<EmployedDtos> mapEmployedEntitiesToDtos(List<EmployedEntity> employedEntities) {
        return employedEntities.stream()
            .map(employedMapper::toDtos)
            .collect(Collectors.toList());
    }
}