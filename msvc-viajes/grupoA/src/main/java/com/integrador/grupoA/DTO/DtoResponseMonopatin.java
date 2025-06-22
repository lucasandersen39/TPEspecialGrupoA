package com.integrador.grupoA.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoResponseMonopatin {
    private String idMonopatin;
    private Long idParada;
    private int estado;           // 0: Inactivo, 1: Activo, 2: Mantenimiento
    private double kmRecorridos;
    private double tiempoUsado;
}