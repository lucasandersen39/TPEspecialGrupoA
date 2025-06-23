package com.integrador.grupoA.Client;

import com.integrador.grupoA.DTO.DtoResponseMonopatin;
import com.integrador.grupoA.DTO.DtoResponseMonopatinesMasViajes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "msvc-monopatines", url = "http://msvc-monopatines:8001/api/monopatin")
public interface MonopatinClient {

    @GetMapping("/detalles")
    List<DtoResponseMonopatinesMasViajes> obtenerDetallesMonopatines(@RequestBody List<String> idsMonopatines);

    @GetMapping("/{id}")
    DtoResponseMonopatin obtenerMonopatinPorId(@PathVariable("id") String idMonopatin);

    @GetMapping("/monopatines/existe/{id}")
    boolean existeMonopatin(@PathVariable("id") String idMonopatin);


}

