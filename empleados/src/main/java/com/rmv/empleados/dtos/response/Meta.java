package com.rmv.empleados.dtos.response;

import java.util.Date;

public record Meta(
    String transactionId,
    String status,
    int statusCode,
    Date timetamp,
    String message
) {
    public Meta(String transactionId, String status, int statusCode, Date timetamp) {
        this(transactionId, status, statusCode, timetamp, null);
    }
}
