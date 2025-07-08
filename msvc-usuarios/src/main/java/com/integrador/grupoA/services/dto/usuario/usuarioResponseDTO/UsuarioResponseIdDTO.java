package com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de respuesta para enviar el id del usuario")
public class UsuarioResponseIdDTO {

    @Schema(description = "id del usuario", example = "5")
    private Long id;
}