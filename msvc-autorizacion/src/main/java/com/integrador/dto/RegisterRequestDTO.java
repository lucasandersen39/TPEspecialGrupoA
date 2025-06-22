package com.integrador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RegisterRequestDTO {
    private String username;
    private Long idUsuario;
    private String password;
    private  Set<String> roles;
}