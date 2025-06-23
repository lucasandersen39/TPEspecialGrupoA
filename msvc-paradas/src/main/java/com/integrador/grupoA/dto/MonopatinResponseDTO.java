package com.integrador.grupoA.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MonopatinResponseDTO {
    private String idMonopatin;
    private int estado;
    private Long idParada;
    private double kmRecorridos;
    private double tiempoUsado;
}
