package com.integrador.grupoA.controllers;

import com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO.UsuarioResponseIdDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.integrador.grupoA.services.UsuarioService;
import com.integrador.grupoA.services.dto.usuario.usuarioRequestDTO.UsuarioRequestDTO;
import com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO.UsuarioResponseDTO;
import com.integrador.grupoA.swagger.SwaggerErrorResponseUsuarios;

import java.util.List;
import java.util.Optional;

@Tag(name = "Usuarios", description = "API para gestión de usuarios")
@RestController
@RequestMapping("api/usuario")
public class UsuarioController implements SwaggerErrorResponseUsuarios {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Listar todos los usuarios")
    @GetMapping("")
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        final var result = usuarioService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Buscar usuario por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UsuarioResponseDTO>> buscarPorId(@PathVariable Long id) {
        final var result = usuarioService.buscarPorId(id);
        return ResponseEntity.accepted().body(result);
    }

    @Operation(summary = "Obtener usuarios por tipo")
    @GetMapping("/tipoUsuario/{tipoUsuario}")
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerUsuariosPorTipo(@PathVariable String tipoUsuario) {
        final var result = usuarioService.obtenerUsuariosPorTipo(tipoUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Crear un nuevo usuario")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<Optional<UsuarioResponseIdDTO>> crearUsuario(@RequestBody @Valid UsuarioRequestDTO usuario) {
        final var result = this.usuarioService.crearUsuario(usuario);
        return ResponseEntity.accepted().body(result);
    }

    @Operation(summary = "Modificar usuario")
    @PutMapping("/{id}")
    public ResponseEntity<Optional<UsuarioResponseDTO>> modificarUsuario(@RequestBody @Valid UsuarioRequestDTO usuario,
                                                                         @PathVariable Long id) {
        final var result = this.usuarioService.modificarUsuario(usuario, id);
        return ResponseEntity.accepted().body(result);
    }

    @Operation(summary = "Eliminar usuario")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }

    @Operation(summary = "Cambiar estado activo del usuario")
    @PutMapping("/estado/{id}")
    public void cambiarActivoUsuario(@PathVariable Long id) {
        this.usuarioService.cambiarActivoUsuario(id);
    }
}