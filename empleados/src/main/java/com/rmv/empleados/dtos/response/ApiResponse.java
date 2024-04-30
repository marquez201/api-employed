package com.rmv.empleados.dtos.response;

public record ApiResponse<T> (
    T data,
    Meta meta
) 
{

}
