package com.integrador.grupoA.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Schema(description = "Respuesta que contiene una parada y sus monopatines asociados")
public class ParadaMonopatinResponseDTO {

    @Schema(description = "Datos de la parada")
    private ParadaResponseDTO parada;

    @Schema(description = "Lista de monopatines cercanos a la parada")
    private List<MonopatinResponseDTO> monopatines = new ArrayList<>();
}
