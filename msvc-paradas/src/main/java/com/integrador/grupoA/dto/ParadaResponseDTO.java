package com.integrador.grupoA.dto;

import com.integrador.grupoA.entities.Parada;
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

    public static ParadaResponseDTO toParadaResponseDTO(Parada p){
        return new ParadaResponseDTO(p.getNombre(),p.getX(),p.getY());
    }
}
