package com.rmv.empleados.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rmv.empleados.entitys.EmployedEntity;

public interface EmployedRepository extends MongoRepository<EmployedEntity, String> {
    
}
