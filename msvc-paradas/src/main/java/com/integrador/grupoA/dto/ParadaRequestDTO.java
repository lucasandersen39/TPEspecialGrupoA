package com.integrador.grupoA.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO para crear o modificar una parada")
public class ParadaRequestDTO {

    @Schema(description = "Nombre de la parada", example = "Plaza Central")
    private String nombre;

    @NotNull(message = "La coordenada X es obligatoria")
    @Schema(description = "Coordenada X de la parada", example = "42.1234")
    private Double x;

    @NotNull(message = "La coordenada Y es obligatoria")
    @Schema(description = "Coordenada Y de la parada", example = "-58.1234")
    private Double y;
}
