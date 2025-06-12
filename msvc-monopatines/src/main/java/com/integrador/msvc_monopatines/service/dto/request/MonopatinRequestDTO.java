package com.integrador.msvc_monopatines.service.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MonopatinRequestDTO {
    private int estado;
    private String coordenadas;
    private LocalDateTime fechaInicioPausa;
    private double kmRecorridos;
    private double tiempoUsado;
}
