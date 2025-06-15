package com.integrador.grupoA.services.dto.tarifaResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TarifaResponseDTO {

    private String tipo_tarifa;
    private double monto;
}
