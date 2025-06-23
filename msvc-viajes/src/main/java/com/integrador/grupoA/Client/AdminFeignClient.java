package com.integrador.grupoA.Client;

import com.integrador.grupoA.DTO.DtoTarifaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-admin", url = "http://msvc-admin:8005/api/admin")
public interface AdminFeignClient {

    @GetMapping("/tarifas/vigente/{tipoTarifa}")
    DtoTarifaResponse obtenerTarifaPorTipo(@PathVariable("tipoTarifa") String tipoTarifa);

    @GetMapping("cuentas/{id}/verificar-saldo")
    boolean verificarSaldoCuenta(@PathVariable("id") Long id);


}
