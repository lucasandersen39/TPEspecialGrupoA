package com.integrador.grupoA.Repositories;

import com.integrador.grupoA.DTO.DtoResponseMonopatinesMasViajes;
import com.integrador.grupoA.DTO.DtoTiempoPausado;
import com.integrador.grupoA.Domain.Viaje;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ViajeRepository  extends JpaRepository<Viaje, Integer> {


    @Query("SELECT v FROM Viaje v WHERE v.fechaInicio BETWEEN :fechaInicio AND :fechaFin")
    List<Viaje> findViajesEntreFechas(@Param("fechaInicio") LocalDateTime fechaInicio,
                                      @Param("fechaFin") LocalDateTime fechaFin);

    @Query("SELECT new com.integrador.grupoA.DTO.DtoTiempoPausado(v.idMonopatin, SUM(v.tiempoPausado)) " +
            "FROM Viaje v GROUP BY v.idMonopatin")
    List<DtoTiempoPausado> obtenerTiemposPausados();

    @Query("SELECT v.idMonopatin, COUNT(v) " +
            "FROM Viaje v " +
            "WHERE YEAR(v.fechaInicio) = :anio " +
            "GROUP BY v.idMonopatin " +
            "HAVING COUNT(v) > :minViajes")
    List<Object[]> findMonopatinesConMasDeXViajesEnAnio(@Param("anio") int anio, @Param("minViajes") Long minViajes);



    @Query("SELECT v.idUsuario, COUNT(v.id) as totalViajes " +
            "FROM Viaje v " +
            "WHERE v.fechaInicio BETWEEN :fechaInicio AND :fechaFin " +
            "GROUP BY v.idUsuario " +
            "ORDER BY totalViajes DESC")
    List<Object[]> findUsuariosConMasViajes(
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin);




}
