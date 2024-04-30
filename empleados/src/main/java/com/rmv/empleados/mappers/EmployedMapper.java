package com.rmv.empleados.mappers;

import org.springframework.stereotype.Component;

import com.rmv.empleados.dtos.EmployedDtos;
import com.rmv.empleados.entitys.EmployedEntity;

@Component
public class EmployedMapper {

    public EmployedDtos toDtos(EmployedEntity employedEntity) {
        return new EmployedDtos(
            employedEntity.getId(),
            employedEntity.getFirstName(),
            employedEntity.getLastName(),
            employedEntity.getStand(),
            employedEntity.getAddressDtos(),
            employedEntity.getEmail()
        );
    }

    public EmployedEntity toEntity(EmployedDtos employedDtos) {
        return new EmployedEntity(
            employedDtos.getId(),
            employedDtos.getFirstName(),
            employedDtos.getLastName(),
            employedDtos.getStand(),
            employedDtos.getAddressDtos(),
            employedDtos.getEmail()
        );
    }
}