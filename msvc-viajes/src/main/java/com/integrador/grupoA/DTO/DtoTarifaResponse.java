package com.integrador.grupoA.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoTarifaResponse {

    private long id;
    private String tipo_tarifa;
    private double monto;
    private LocalDate fecha_vigencia;

}
