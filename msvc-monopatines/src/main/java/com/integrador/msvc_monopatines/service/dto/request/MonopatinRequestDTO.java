package com.integrador.msvc_monopatines.service.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MonopatinRequestDTO {
    private int estado;
    private Long idParada;
    private double kmRecorridos;
    private double tiempoUsado;
}
