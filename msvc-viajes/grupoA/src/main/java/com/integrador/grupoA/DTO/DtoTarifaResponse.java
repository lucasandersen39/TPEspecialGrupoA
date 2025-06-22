package com.integrador.grupoA.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoTarifaResponse {

    private long id;
    private String tipo_tarifa;
    private double monto;

}
