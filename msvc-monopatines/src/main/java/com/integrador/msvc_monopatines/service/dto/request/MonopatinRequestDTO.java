package com.integrador.msvc_monopatines.service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Data
public class MonopatinRequestDTO {
    @Schema(
            description = "Estado del monopatín (0=DISPONIBLE, 1=EN_USO, 2=EN_MANTENIMIENTO)",
            example = "1",
            minimum = "0",
            maximum = "2"
    )
    @Min(value = 0, message = "El estado solo puede ser 0 (disponible), 1 (en uso) o 2 (mantenimiento)")
    @Max(value = 2, message = "El estado solo puede ser 0 (disponible), 1 (en uso) o 2 (mantenimiento)")
    private int estado;
    @Schema(description = "ID de la parada donde se encuentra", example = "1")
    private Long idParada;
    @Schema(description = "Cantidad de kilómetros recorridos", example = "12.5")
    private double kmRecorridos;
    @Schema(description = "Tiempo total de uso en minutos", example = "65.0")
    private double tiempoUsado;
}
