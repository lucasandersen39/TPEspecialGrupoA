package com.integrador.msvc_monopatines.service.dto.response;

import com.integrador.msvc_monopatines.domain.Monopatin;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MonopatinResponseDTO {
    private String idMonopatin;
    private int estado;
    private String coordenadas;
    private double kmRecorridos;
    private double tiempoUsado;

    public MonopatinResponseDTO (Monopatin m) {
        this.idMonopatin = m.getIdMonopatin();
        this.estado = m.getEstado();
        this.coordenadas = m.getCoordenadas();
        this.kmRecorridos = m.getKmRecorridos();
        this.tiempoUsado = m.getTiempoUsado();
    }
}
