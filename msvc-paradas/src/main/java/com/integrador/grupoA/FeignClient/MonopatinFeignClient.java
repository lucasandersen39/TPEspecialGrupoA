package com.integrador.grupoA.FeignClient;


import com.integrador.grupoA.dto.MonopatinResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@FeignClient(name = "msvc-monopatines", url = "http://msvc-monopatines:8001/api/monopatin")
public interface MonopatinFeignClient {

    @GetMapping("/disponibles")
    List<MonopatinResponseDTO> getAllMonopatines();
}
