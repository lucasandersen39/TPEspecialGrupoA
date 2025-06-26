package com.integrador.grupoA.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor

public class DtoViajeRequest {

    @NotNull(message = "El id del usuario es obligatorio.")
    @Positive(message = "El id del usuario debe ser un número positivo.")
    private Long idUsuario;

    @NotNull(message = "El id del monopatín es obligatorio.")
    private String idMonopatin;

    @NotNull(message = " inicio obligatorio.")
    private String fechaInicio;

    @NotNull(message = "fin obligatorio.")
    private String fechaFin;


    @NotNull(message = "Los kilómetros recorridos son obligatorios.")
    @Positive(message = "Los kilómetros recorridos deben ser un número positivo.")
    private double kmRecorridos;


    private double tiempoPausado;


//    //no deberia estar
//    @NotNull(message = "El costo total no puede ser nulo.")
//    @Positive(message = "El costo total debe ser un número positivo.")
//    private double costoTotal;


}
