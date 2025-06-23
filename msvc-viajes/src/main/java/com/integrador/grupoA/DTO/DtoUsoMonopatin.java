package com.integrador.grupoA.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUsoMonopatin {
    private int idUsuario;       // ID del usuario
    private long totalViajes;    // Número de viajes realizados
    private double totalKm;      // Kilómetros recorridos
    private double totalTiempo;  // Tiempo de uso en minutos (puedes transformarlo a horas si lo necesitas)
}