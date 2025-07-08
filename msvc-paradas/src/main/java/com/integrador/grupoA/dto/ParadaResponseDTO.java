package com.integrador.grupoA.dto;

import com.integrador.grupoA.entities.Parada;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para devolver datos de una parada")
public class ParadaResponseDTO {

    @Schema(description = "Nombre de la parada", example = "Plaza Central")
    private String nombre;

    @Schema(description = "Coordenada X", example = "42.1234")
    private Double x;

    @Schema(description = "Coordenada Y", example = "-58.1234")
    private Double y;

    public static ParadaResponseDTO toParadaResponseDTO(Parada p){
        return new ParadaResponseDTO(p.getNombre(),p.getX(),p.getY());
    }
}
