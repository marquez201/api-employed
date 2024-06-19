package com.rmv.empleados.mappers;

import org.springframework.stereotype.Component;

import com.rmv.empleados.dtos.AddressDtos;
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
        AddressDtos addressDtos = employedDtos.getAddressDtos();
        AddressDtos transformedAddressDtos = new AddressDtos(
            addressDtos.getStreet().toUpperCase(),
            addressDtos.getNumber(),
            addressDtos.getColony().toUpperCase()
        );

        return new EmployedEntity(
            employedDtos.getId(),
            employedDtos.getFirstName().toUpperCase(),
            employedDtos.getLastName().toUpperCase(),
            employedDtos.getStand().toUpperCase(),
            transformedAddressDtos,
            employedDtos.getEmail()
        );
    }
}