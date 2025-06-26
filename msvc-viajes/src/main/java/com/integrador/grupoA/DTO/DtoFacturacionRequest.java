package com.integrador.grupoA.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DtoFacturacionRequest {

        private LocalDateTime fechaInicio;
        private LocalDateTime fechaFin;



}
