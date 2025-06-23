package com.integrador.grupoA.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@RequiredArgsConstructor
public class DtoViajeRequest {

    @NotNull(message = "El id del usuario es obligatorio.")
    @Positive(message = "El id del usuario debe ser un número positivo.")
    private int idUsuario;

    @NotNull(message = "El id del monopatín es obligatorio.")
    @Positive(message = "El id del monopatín debe ser un número positivo.")
    private String idMonopatin;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // Indica el formato esperado
    private LocalDateTime fechaInicio;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // Indica el formato esperado
    private LocalDateTime fechaFin;


    @NotNull(message = "Los kilómetros recorridos son obligatorios.")
    @Positive(message = "Los kilómetros recorridos deben ser un número positivo.")
    private double kmRecorridos;

    //no deberia estar
    @NotNull(message = "El costo total no puede ser nulo.")
    @Positive(message = "El costo total debe ser un número positivo.")
    private double costoTotal;


    public DtoViajeRequest(int idUsuario, String idMonopatin, LocalDateTime fechaInicio,
                           LocalDateTime fechaFin, double kmRecorridos, double costoTotal) {
        this.idUsuario = idUsuario;
        this.idMonopatin = idMonopatin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.kmRecorridos = kmRecorridos;
        this.costoTotal = costoTotal;
    }
}
