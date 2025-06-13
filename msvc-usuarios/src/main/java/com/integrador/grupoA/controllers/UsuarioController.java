package com.integrador.grupoA.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.integrador.grupoA.services.UsuarioService;
import com.integrador.grupoA.services.dto.usuario.usuarioRequestDTO.UsuarioRequestDTO;
import com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO.UsuarioResponseDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
    //hay URL que no retornan nada, pero cualquier cosa las modificamos para probar

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public List<UsuarioResponseDTO> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public Optional<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @PostMapping("")
    public ResponseEntity<Optional<UsuarioResponseDTO>> crearUsuario(@RequestBody @Valid UsuarioRequestDTO usuario){
        final var result = this.usuarioService.crearUsuario(usuario);
        return ResponseEntity.accepted().body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<UsuarioResponseDTO>> modificarUsuario(@RequestBody @Valid UsuarioRequestDTO usuario, @PathVariable Long id){
        final var result = this.usuarioService.modificarUsuario(usuario, id);
        return ResponseEntity.accepted().body(result);
    }

    @DeleteMapping ("/{id}")
    public void eliminarUsuario(@PathVariable Long id){
        this.usuarioService.eliminarUsuario(id);
    }

    @PutMapping("/estado/{id}")
    public void cambiarEstadoUsuario(@PathVariable Long id){
        this.usuarioService.cambiarEstadoUsuario(id);
    }
}
