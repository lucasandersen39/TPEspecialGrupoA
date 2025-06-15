package com.integrador.grupoA.repository;

import com.integrador.grupoA.entities.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.integrador.grupoA.services.dto.tarifaResponse.TarifaResponseDTO;
import java.util.List;

@Repository
public interface TarifaRepository extends JpaRepository <Tarifa, Long> {

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Tarifa t WHERE t.tipo_tarifa = :tipoTarifa")
    boolean existeTarifaPorTipo(@Param("tipoTarifa") String tipoTarifa);

    @Query("SELECT new com.integrador.grupoA.services.dto.tarifaResponse.TarifaResponseDTO(t.tipo_tarifa, t.monto) FROM Tarifa t")
    List<TarifaResponseDTO> findAllTarifas();

    @Query("SELECT new com.integrador.grupoA.services.dto.tarifaResponse.TarifaResponseDTO(t.tipo_tarifa, t.monto) FROM Tarifa t WHERE t.tipo_tarifa = :tipo_tarifa")
    TarifaResponseDTO findTarifaPorTipo(@Param("tipo_tarifa") String tipo_tarifa);

    @Query("SELECT new com.integrador.grupoA.services.dto.tarifaResponse.TarifaResponseDTO(t.tipo_tarifa, t.monto) FROM Tarifa t WHERE t.id = :id")
    TarifaResponseDTO findTarifaPorId(@Param("id") Long id);

}
