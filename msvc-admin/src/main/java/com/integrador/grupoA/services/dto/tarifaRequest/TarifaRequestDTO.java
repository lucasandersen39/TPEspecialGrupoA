package com.integrador.grupoA.services.dto.tarifaRequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class TarifaRequestDTO {
    @NotNull(message = "El campo id no puede estar vacío")
    private Long id;

    @NotBlank(message = "El campo tipo_tarifa no puede estar vacío")
    private String tipo_tarifa;

    @NotNull(message = "El campo monto no puede estar vacío")
    @Positive(message = "El monto debe ser positivo")
    private double monto;
}
