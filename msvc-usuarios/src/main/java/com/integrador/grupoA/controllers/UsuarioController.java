package com.integrador.grupoA.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        final var result = usuarioService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UsuarioResponseDTO>> buscarPorId(@PathVariable Long id) {
        final var result = usuarioService.buscarPorId(id);
        return ResponseEntity.accepted().body(result);
    }

    @GetMapping("/tipoUsuario/{tipoUsuario}")
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerUsuariosPorTipo(@PathVariable String tipoUsuario) {
        final var result = usuarioService.obtenerUsuariosPorTipo(tipoUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(result);
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }

    @PutMapping("/estado/{id}")
    public void cambiarEstadoUsuario(@PathVariable Long id){
        this.usuarioService.cambiarEstadoUsuario(id);
    }
}
