package com.rmv.empleados.dtos.response;

public enum ApiMessage {
    EMPLEADO_REGISTRADO("EMPLEADO REGISTRADO EXITOSAMENTE"),
    EMPLEADO_ELIMINADO("EMPLEADO ELIMINADO EXITOSAMENTE"),
    EMPLEADO_NO_ENCONTRADO("EMPLEADO NO ENCONTRADO"),
    FALLO_AL_ACTUALIZAR("FALLO AL ACTUALIZAR EMPLEADO"),
    FALLO_AL_ELIMINAR("FALLO AL ELIMINAR EMPLEADO");

    private final String msgString;

    ApiMessage(String msgString) {
        this.msgString = msgString;
    }

    public String getMessage() {
        return msgString;
    }
}
