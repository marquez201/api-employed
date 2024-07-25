package com.rmv.empleados.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDtos implements Serializable{
    @NotBlank(message = "CAMPO REQUERIDO")
    String street;
    @NotBlank(message = "CAMPO REQUERIDO")
    short number;
    @NotBlank(message = "CAMPO REQUERIDO")
    String colony;
}