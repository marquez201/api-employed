package com.rmv.empleados.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rmv.empleados.entitys.EmployedEntity;

public interface EmployedRepository extends MongoRepository<EmployedEntity, String> {
    
    List<EmployedEntity> findByFirstName(String firstName);

    Optional<EmployedEntity> findByEmail(String email);
}
