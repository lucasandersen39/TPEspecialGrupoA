package com.integrador.grupoA.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ParadaMonopatinResponseDTO {
    private ParadaResponseDTO parada;
    private List<MonopatinResponseDTO> monopatines = new ArrayList<>();
}
