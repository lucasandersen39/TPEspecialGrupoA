package com.integrador.grupoA.services.dto.parada.paradaResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ParadaResponseDTO {

    private String nombre;
    private Double x;
    private Double y;
}
