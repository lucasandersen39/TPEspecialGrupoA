package com.integrador.grupoA.Client;

import com.integrador.grupoA.DTO.DtoUsuario;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-usuarios", url = "http://localhost:8002/api/usuario")
public interface UsuarioFeignClient {

    @GetMapping("/{id}")
    DtoUsuario obtenerUsuarioPorId(@PathVariable("id") int id);

}
