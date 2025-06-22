package com.integrador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UsuarioRequestDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String nombreUsuario;
    private String tipoUsuario;
    private Double dinero;
    private LocalDateTime fechaAlta;
    private boolean activo;
}
