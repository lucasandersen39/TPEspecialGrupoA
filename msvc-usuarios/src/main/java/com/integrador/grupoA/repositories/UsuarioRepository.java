package com.integrador.grupoA.repositories;

import com.integrador.grupoA.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO.UsuarioResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //findByNombreUsuario
    //findByEmail

    @Query("SELECT new com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO.UsuarioResponseDTO(" +
                  "u.nombre, u.apellido, u.email, u.telefono, u.tipoUsuario, u.nombreUsuario, u.x, u.y, u.dinero, u.fechaAlta, u.estado) " +
                  "FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
    Optional<UsuarioResponseDTO> findByNombreUsuario(String nombreUsuario);


    @Query("SELECT new com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO.UsuarioResponseDTO(" +
            "u.nombre, u.apellido, u.email, u.telefono, u.tipoUsuario, u.nombreUsuario, u.x, u.y, u.dinero, u.fechaAlta, u.estado) " +
            "FROM Usuario u WHERE u.email = :email")
    Optional<UsuarioResponseDTO> findByEmail(String email);


    @Query("SELECT new com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO.UsuarioResponseDTO(" +
            "u.nombre, u.apellido, u.email, u.telefono, u.tipoUsuario, u.nombreUsuario, u.x, u.y, u.dinero, u.fechaAlta, u.estado) " +
            "FROM Usuario u")
    List<UsuarioResponseDTO> traerUsuariosDTO();

    //buscarPorId
    @Query("SELECT new com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO.UsuarioResponseDTO(" +
            "u.nombre, u.apellido, u.email, u.telefono, u.tipoUsuario, u.nombreUsuario, u.x, u.y, u.dinero, u.fechaAlta, u.estado) " +
            "FROM Usuario u WHERE u.id = :id")
    Optional<UsuarioResponseDTO> buscarPorId(Long id);

    //obtenerUsuariosPorTipo
    @Query("SELECT new com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO.UsuarioResponseDTO(" +
            "u.nombre, u.apellido, u.email, u.telefono, u.tipoUsuario, u.nombreUsuario, u.x, u.y, u.dinero, u.fechaAlta, u.estado) " +
            "FROM Usuario u WHERE u.tipoUsuario = :tipoUsuario")
    List<UsuarioResponseDTO> obtenerUsuariosPorTipo(String tipoUsuario);

}