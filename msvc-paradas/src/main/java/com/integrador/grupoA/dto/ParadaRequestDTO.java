package com.integrador.grupoA.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Getter
@Setter
public class ParadaRequestDTO {

    private String nombre;

    @NotNull(message = "La coordenada X es obligatoria")
    private Double x;

    @NotNull(message = "La coordenada Y es obligatoria")
    private Double y;
}
