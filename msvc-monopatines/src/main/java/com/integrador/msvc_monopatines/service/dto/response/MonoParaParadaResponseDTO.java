package com.integrador.msvc_monopatines.service.dto.response;

import com.integrador.msvc_monopatines.domain.Monopatin;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MonoParaParadaResponseDTO {
    private String idMonopatin;
    private int estado;
    private Long idParada;

    public MonoParaParadaResponseDTO (Monopatin m) {
        this.idMonopatin = m.getIdMonopatin();
        this.estado = m.getEstado();
        this.idParada = m.getIdParada();
    }

}
