package com.rmv.empleados.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rmv.empleados.controller.advice.ResourceNotFoundException;
import com.rmv.empleados.dtos.AddressDtos;
import com.rmv.empleados.dtos.EmployedDtos;
import com.rmv.empleados.dtos.response.ApiMessage;
import com.rmv.empleados.entitys.EmployedEntity;
import com.rmv.empleados.mappers.EmployedMapper;
import com.rmv.empleados.repository.EmployedRepository;

@Service
public class EmployedService {
    @Autowired
    EmployedMapper employedMapper;

    @Autowired
    EmployedRepository employedRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional(readOnly = true)
    public List<EmployedDtos> getAllEmployed() {
        logger.info("***INICIA METODO getAllEmployed()***");
        return mapEmployedEntitiesToDtos(employedRepository.findAll());
    }

    @Transactional
    public EmployedDtos saveEmployed(EmployedDtos employedDtos) {
        logger.info("***INICIA METODO saveEmployed()***");
        EmployedEntity employedEntity = employedMapper.toEntity(employedDtos);
        employedEntity = employedRepository.save(employedEntity);
        return employedMapper.toDtos(employedEntity);
    }

    @Transactional(readOnly = true)
    public List<EmployedDtos> findEmploydFirstName(String firstName) {
        logger.info("***INICIA METODO findEmploydFirstName([FIRSTNAME: {}])", firstName);
        return mapEmployedEntitiesToDtos(employedRepository.findByFirstName(firstName));
    }

    @Transactional(readOnly = true)
    public EmployedDtos findEmploydId(String id) {
        logger.info("***INICIA METODO findEmploydId([ID: {}])", id);
        Optional<EmployedEntity> employeeOptional = employedRepository.findById(id);
        if (employeeOptional.isPresent()) {
            return employedMapper.toDtos(employeeOptional.get());
        } else {
            throw new ResourceNotFoundException(ApiMessage.EMPLEADO_NO_ENCONTRADO.getMessage());
        }
    }

    @Transactional
    public EmployedDtos updateEmployed(EmployedDtos employedDtos) {
        logger.info("***INICIA METODO updateEmployed()***");
        Optional<EmployedEntity> emOptional = employedRepository.findById(employedDtos.getId());
        if (emOptional.isPresent()) {
            EmployedEntity entity = emOptional.get();

            entity.setFirstName(employedDtos.getFirstName().toUpperCase());
            entity.setLastName(employedDtos.getLastName().toUpperCase());
            entity.setStand(employedDtos.getStand().toUpperCase());

            AddressDtos addressDtos = employedDtos.getAddressDtos();
            AddressDtos transformedAddressDtos = new AddressDtos(
                    addressDtos.getStreet().toUpperCase(),
                    addressDtos.getNumber(),
                    addressDtos.getColony().toUpperCase());

            entity.setAddressDtos(transformedAddressDtos);

            entity = employedRepository.save(entity);

            return employedMapper.toDtos(entity);
        } else {
            throw new ResourceNotFoundException(ApiMessage.FALLO_AL_ACTUALIZAR.getMessage());
        }
    }

    @Transactional
    public String deleteEmployed(String id) {
        logger.info("***INICIA METODO deleteEmployd([ID: {}])", id);
        Optional<EmployedEntity> emOptional = employedRepository.findById(id);
        if (emOptional.isPresent()) {
            EmployedEntity entity = emOptional.get();
            employedRepository.delete(entity);
            return ApiMessage.EMPLEADO_ELIMINADO.getMessage();
        } else {
            throw new ResourceNotFoundException(ApiMessage.FALLO_AL_ELIMINAR.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public EmployedDtos findEmployedByEmail(String email) {
        logger.info("***INICIA METODO findEmploydByEmail([EMAIL: {}])", email);
        Optional<EmployedEntity> employedEntity = employedRepository.findByEmail(email);
        if (employedEntity.isPresent()) {
            return employedMapper.toDtos(employedEntity.get());
        } else {
            throw new ResourceNotFoundException(ApiMessage.EMPLEADO_NO_ENCONTRADO.getMessage());
        }
    }

    private List<EmployedDtos> mapEmployedEntitiesToDtos(List<EmployedEntity> employedEntities) {
        return employedEntities.stream()
                .map(employedMapper::toDtos)
                .collect(Collectors.toList());
    }
}