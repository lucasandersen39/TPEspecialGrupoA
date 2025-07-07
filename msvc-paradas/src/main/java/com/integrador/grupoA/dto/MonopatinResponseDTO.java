package com.integrador.grupoA.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO que representa un monopatín")
public class MonopatinResponseDTO {

    @Schema(description = "Identificador del monopatín", example = "MONO123")
    private String idMonopatin;

    @Schema(description = "Estado del monopatín (0=Libre, 1=En uso, 2=En mantenimiento)", example = "0")
    private int estado;

    @Schema(description = "ID de la parada asociada", example = "5")
    private Long idParada;

}
