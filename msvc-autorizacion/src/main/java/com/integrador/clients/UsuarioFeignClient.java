package com.integrador.clients;


import com.integrador.dto.UsuarioRequestDTO;
import com.integrador.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Optional;

@FeignClient(name = "msvc-usuarios", url = "http://msvc-usuarios:8002/api/usuario")
public interface UsuarioFeignClient {

    @PostMapping
    ResponseEntity<Optional<UsuarioResponseDTO>> crearUsuario(@RequestBody @Valid UsuarioRequestDTO usuario);
}
