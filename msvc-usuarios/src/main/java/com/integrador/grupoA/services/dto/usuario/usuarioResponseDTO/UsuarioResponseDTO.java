package com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de respuesta para datos del usuario")
public class UsuarioResponseDTO {

    @Schema(description = "Nombre del usuario", example = "Federico")
    private String nombre;

    @Schema(description = "Apellido del usuario", example = "Cordeiro")
    private String apellido;

    @Schema(description = "Correo electrónico del usuario", example = "federico@mail.com")
    private String email;

    @Schema(description = "Número de teléfono", example = "+2262-579632")
    private String telefono;

    @Schema(description = "Tipo de usuario", example = "Basico")
    private String tipoUsuario;

    @Schema(description = "Nombre de usuario para login", example = "fedeCor")
    private String nombreUsuario;

    @Schema(description = "Saldo disponible", example = "2500.75")
    private Double dinero;

    @Schema(description = "Fecha de alta del usuario", example = "2024-06-23T19:30:00")
    private LocalDateTime fechaAlta;

    @Schema(description = "Indica si el usuario está activo", example = "true")
    private boolean activo;
}