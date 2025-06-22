package com.integrador.grupoA.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DtoViajeResponse {


        private int id;

        private int idUsuario;

        private String idMonopatin;

        private LocalDateTime fechaInicio;

        private LocalDateTime fechaFin;

        private double kmRecorridos;

        private double costoTotal;


}

