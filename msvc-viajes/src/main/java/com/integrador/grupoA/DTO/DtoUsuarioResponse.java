package com.integrador.grupoA.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUsuarioResponse {
    private Long idUsuario;
    private String tipoUsuario;

}