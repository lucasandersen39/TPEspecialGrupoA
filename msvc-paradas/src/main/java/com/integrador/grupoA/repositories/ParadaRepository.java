package com.integrador.grupoA.repositories;

import com.integrador.grupoA.entities.Parada;
import com.integrador.grupoA.services.dto.parada.paradaResponseDTO.ParadaResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ParadaRepository extends JpaRepository<Parada, Long> {

    @Query("SELECT new com.integrador.grupoA.services.dto.parada.paradaResponseDTO.ParadaResponseDTO(" +
            "p.nombre, p.x, p.y) " +
            "FROM Parada p WHERE p.id = :id")
    Optional<ParadaResponseDTO> buscarPorId(Long id);

    @Query("SELECT new com.integrador.grupoA.services.dto.parada.paradaResponseDTO.ParadaResponseDTO(" +
            "p.nombre, p.x, p.y) " +
            "FROM Parada p")
    List<ParadaResponseDTO> traerParadas();

    @Query("SELECT new com.integrador.grupoA.services.dto.parada.paradaResponseDTO.ParadaResponseDTO(" +
            "p.nombre, p.x, p.y) " +
            "FROM Parada p WHERE ABS(p.x - :x) < 0.0001 AND ABS(p.y - :y) < 0.0001")
    Optional<ParadaResponseDTO> buscarPorCoordenadas(@Param("x")Double x,@Param("y") Double y);
}
