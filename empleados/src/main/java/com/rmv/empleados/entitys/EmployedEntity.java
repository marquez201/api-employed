package com.rmv.empleados.entitys;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.rmv.empleados.dtos.AddressDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "empleados")
public class EmployedEntity {
    
    @MongoId
    @Field(targetType = FieldType.OBJECT_ID, name = "_id")
    String id;

    @Field(targetType = FieldType.STRING, name = "firstName")
    String firstName;

    @Field(targetType = FieldType.STRING, name = "lastName")
    String lastName;

    @Field(targetType = FieldType.STRING, name = "stand")
    String stand;

    @Field(name = "address")
    AddressDtos addressDtos;

    @Field(targetType = FieldType.STRING, name = "email")
    String email;
}