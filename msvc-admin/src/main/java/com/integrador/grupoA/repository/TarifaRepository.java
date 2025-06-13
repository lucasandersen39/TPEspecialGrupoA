package com.integrador.grupoA.repository;

import com.integrador.grupoA.entities.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaRepository extends JpaRepository <Tarifa, Long> {

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Tarifa t WHERE t.tipo_tarifa = :tipoTarifa")
    boolean existeTarifaPorTipo(@Param("tipoTarifa") String tipoTarifa);
}
