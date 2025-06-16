package com.integrador.grupoA.services;

import com.integrador.grupoA.dto.MonopatinResponseDTO;
import com.integrador.grupoA.dto.ParadaRequestDTO;
import com.integrador.grupoA.dto.ParadaResponseDTO;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface ParadaService {

    List<ParadaResponseDTO> listar();

    Optional<ParadaResponseDTO> buscarPorId(Long id);

    Optional<ParadaResponseDTO> crearParada(@Valid ParadaRequestDTO parada);

    Optional<ParadaResponseDTO> modificarParada(@Valid ParadaRequestDTO parada, Long id);

    void eliminarParada(Long id);

    Optional<ParadaResponseDTO> buscarPorCoordenada(Double x, Double y);

    List<MonopatinResponseDTO> buscarMonopatinesCercanos(Double x, Double y);
}
