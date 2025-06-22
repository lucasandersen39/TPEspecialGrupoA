package com.integrador.msvc_monopatines.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-paradas", url="http://localhost:8003/api/parada")
public interface ParadaFeignClient {
    @GetMapping("/id_valido/{id}")
    ResponseEntity<Void> validarParada(@PathVariable("id") Long id);
}
