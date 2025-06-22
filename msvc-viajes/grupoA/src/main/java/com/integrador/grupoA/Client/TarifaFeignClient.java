package com.integrador.grupoA.Client;

import com.integrador.grupoA.DTO.DtoTarifa;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-admin", url = "http://localhost:8005/api/admin/tarifas")
public interface TarifaFeignClient {

    @GetMapping("/{tipoTarifa}")
    DtoTarifa obtenerTarifaPorTipo(@PathVariable("tipoTarifa") String tipoTarifa);


}
