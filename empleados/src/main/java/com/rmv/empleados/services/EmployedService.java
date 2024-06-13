package com.rmv.empleados.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rmv.empleados.controller.advice.ResourceNotFoundException;
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
    public EmployedDtos findEmploydId(String id) {
        Optional<EmployedEntity> employeeOptional = employedRepository.findById(id);
        if (employeeOptional.isPresent()) {
            return employedMapper.toDtos(employeeOptional.get());
        } else {
            throw new ResourceNotFoundException("Empleado no encontrado con id: " + id);
        }
    }

    @Transactional
    public EmployedDtos updateEmployed(EmployedDtos employedDtos) {
        Optional<EmployedEntity> emOptional = employedRepository.findById(employedDtos.getId());
        if(emOptional.isPresent()) {
            EmployedEntity entity = emOptional.get();

            entity.setFirstName(employedDtos.getFirstName());
            entity.setLastName(employedDtos.getLastName());
            entity.setStand(employedDtos.getStand());
            entity.setAddressDtos(employedDtos.getAddressDtos());

            entity = employedRepository.save(entity);

            return employedMapper.toDtos(entity);
        } else {
            throw new ResourceNotFoundException("Fallo al Actualizar");
        }
    }


    @Transactional
    public String deleteEmployed(String id) {
        Optional<EmployedEntity> emOptional = employedRepository.findById(id);
        if (emOptional.isPresent()) {
            EmployedEntity entity = emOptional.get();
            employedRepository.delete(entity);
            return "Empleado Eliminado";
        } else {
            throw new ResourceNotFoundException("Fallo al Eliminar");
        }
    }

    @Transactional(readOnly = true)
    public EmployedDtos findEmployedByEmail(String email) {
        Optional<EmployedEntity> employedEntity = employedRepository.findByEmail(email);
        if (employedEntity.isPresent()) {
            return employedMapper.toDtos(employedEntity.get());
        } else {
            throw new ResourceNotFoundException("No se encontro");
        }
    }

    private List<EmployedDtos> mapEmployedEntitiesToDtos(List<EmployedEntity> employedEntities) {
        return employedEntities.stream()
            .map(employedMapper::toDtos)
            .collect(Collectors.toList());
    }
}