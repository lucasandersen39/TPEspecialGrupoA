package com.integrador.grupoA.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoResponseUsuarioConViajes {
    private Long idUsuario;          // Identificador único del usuario
    private long totalViajes;       // Cantidad de viajes realizados
    private String tipoUsuario;     // Tipo de usuario (ej. "Premium", "Regular")
}