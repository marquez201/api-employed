package com.rmv.empleados.entitys;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.mongodb.lang.NonNull;
import com.rmv.empleados.dtos.AddressDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "CAMPO REQUERIDO")
    @NonNull
    String firstName;

    @Field(targetType = FieldType.STRING, name = "lastName")
    @NotBlank(message = "CAMPO REQUERIDO")
    @NonNull
    String lastName;

    @Field(targetType = FieldType.STRING, name = "stand")
    @NotBlank(message = "CAMPO REQUERIDO")
    @NonNull
    String stand;

    @Field(name = "address")
    @NotBlank(message = "CAMPO REQUERIDO")
    @NonNull
    AddressDtos addressDtos;

    @Field(targetType = FieldType.STRING, name = "email")
    @Email(message = "CORREO INVALIDO")
    @NonNull
    String email;
}