package com.integrador.msvc_monopatines.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "msvc-viajes", url="http://localhost:8007/api/viaje")
public interface ViajeFeignClient {
    @GetMapping("/viajes/pausas")
    Map<String, Double> obtenerTiempoPausadoTotal();
}
