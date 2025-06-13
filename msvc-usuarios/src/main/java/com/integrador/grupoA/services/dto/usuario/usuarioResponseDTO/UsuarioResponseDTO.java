package com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String tipoUsuario;
    private String nombreUsuario;
    private Double x;
    private Double y;
    private Double dinero;
    private LocalDateTime fechaAlta;
    private boolean estado;
}
