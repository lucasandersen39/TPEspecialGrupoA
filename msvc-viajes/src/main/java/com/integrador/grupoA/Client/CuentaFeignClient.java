package com.integrador.grupoA.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-admin", url = "http://msvc-admin:8005/api/cuenta")
public interface CuentaFeignClient {

    @GetMapping("/{id}/verificar-saldo")
    boolean verificarSaldoCuenta(@PathVariable("id") Long id);

}
