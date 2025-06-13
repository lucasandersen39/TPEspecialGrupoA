package com.integrador.grupoA.services;

import com.integrador.grupoA.services.dto.usuario.usuarioRequestDTO.UsuarioRequestDTO;
import com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO.UsuarioResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<UsuarioResponseDTO> listar();
    Optional<UsuarioResponseDTO> buscarPorId(Long idUsuario);
    Optional<UsuarioResponseDTO> crearUsuario(UsuarioRequestDTO usuario);
    Optional<UsuarioResponseDTO> modificarUsuario(UsuarioRequestDTO usuario, Long idUsuario);
    void eliminarUsuario(Long id);
    void cambiarEstadoUsuario(Long id);
}
