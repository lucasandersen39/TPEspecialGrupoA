package com.integrador.grupoA.repositories;

import com.integrador.grupoA.entities.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ParadaRepository extends JpaRepository<Parada, Long> {

    @Query("SELECT p FROM Parada p WHERE ABS(p.x - :x) < 0.0001 AND ABS(p.y - :y) < 0.0001")
    Optional<Parada> buscarPorCoordenadas(@Param("x") Double x, @Param("y") Double y);

}
