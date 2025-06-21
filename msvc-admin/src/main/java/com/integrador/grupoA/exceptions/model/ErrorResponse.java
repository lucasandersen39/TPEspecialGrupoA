package com.integrador.grupoA.exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String codigo;
    private String mensaje;

    public ErrorResponse(LocalDateTime now, int value, String conflict, String message, String description) {
        this.codigo = String.valueOf(value);
        this.mensaje = conflict + ": " + message + " - " + description;
    }
}

