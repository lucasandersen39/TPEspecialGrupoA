package com.integrador.grupoA.exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String codigo;
    private String mensaje;
}

