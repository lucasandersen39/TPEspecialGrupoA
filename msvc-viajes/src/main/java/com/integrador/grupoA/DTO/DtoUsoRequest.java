package com.integrador.grupoA.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUsoRequest {
    private String idUsuario;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

}