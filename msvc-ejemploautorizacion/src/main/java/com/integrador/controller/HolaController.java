package com.integrador.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prueba-auth")
public class HolaController {
    @GetMapping("/hola")
    public String holaPublico() {
        return "¡Hola! Este endpoint es público.";
    }

    @GetMapping("/hola1")
    @PreAuthorize("hasRole('USER')")
    public String holaUsuario() {
        return "¡Hola, Usuario! Tienes acceso a este recurso.";
    }

    @GetMapping("/hola2")
    @PreAuthorize("hasRole('ADMIN')")
    public String holaAdmin() {
        return "¡Hola, Administrador! Tienes acceso a este recurso restringido.";
    }
}