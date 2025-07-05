package com.integrador.dto;


public record UsuarioRequestDTO(
        String nombre,
        String apellido,
        String email,
        String telefono,
        String nombreUsuario
) {}