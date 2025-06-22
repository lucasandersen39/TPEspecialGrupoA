package com.integrador.msvc_monopatines.service.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ReporteUsoDTO {
    private String idMonopatin;
    private double kmRecorridos;
    private Double tiempoTotalPausado; // puede ser null si no se incluye
}
