package com.integrador.msvc_monopatines.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
@FeignClient(name = "msvc-viajes", url="http://msvc-viajes:8007/api/viaje")
public interface ViajeFeignClient {
    @GetMapping("/pausas")
    Map<String, Double> obtenerTiempoPausadoTotal();
}
