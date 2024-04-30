package com.rmv.empleados.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployedDtos implements Serializable{
    String id;
    String firstName;
    String lastName;
    String stand;
    AddressDtos addressDtos;
    String email;
}