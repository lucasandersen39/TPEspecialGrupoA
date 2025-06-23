package com.integrador.grupoA.FeignClient;


import com.integrador.grupoA.dto.MonopatinResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@FeignClient(name = "msvc-monopatines", url="http://localhost:8003/api/monopatin")

public interface MonopatinFeignClient {

    @GetMapping("/api/monopatin")
    List<MonopatinResponseDTO> getAllMonopatines();
}
