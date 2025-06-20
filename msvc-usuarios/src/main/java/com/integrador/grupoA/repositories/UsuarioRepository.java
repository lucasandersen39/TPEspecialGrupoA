package com.integrador.grupoA.repositories;

import com.integrador.grupoA.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO.UsuarioResponseDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT new com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO.UsuarioResponseDTO(" +
                  "u.nombre, u.apellido, u.email, u.telefono, u.tipoUsuario, u.nombreUsuario, u.dinero, u.fechaAlta, u.activo) " +
                  "FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
    Optional<UsuarioResponseDTO> findByNombreUsuario(@Param(":nombreUsuario") String nombreUsuario);


    @Query("SELECT new com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO.UsuarioResponseDTO(" +
            "u.nombre, u.apellido, u.email, u.telefono, u.tipoUsuario, u.nombreUsuario, u.dinero, u.fechaAlta, u.activo) " +
            "FROM Usuario u WHERE u.email = :email")
    Optional<UsuarioResponseDTO> findByEmail(@Param(":email") String email);


    @Query("SELECT new com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO.UsuarioResponseDTO(" +
            "u.nombre, u.apellido, u.email, u.telefono, u.tipoUsuario, u.nombreUsuario, u.dinero, u.fechaAlta, u.activo) " +
            "FROM Usuario u")
    List<UsuarioResponseDTO> traerUsuariosDTO();

    //buscarPorId
    @Query("SELECT new com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO.UsuarioResponseDTO(" +
            "u.nombre, u.apellido, u.email, u.telefono, u.tipoUsuario, u.nombreUsuario, u.dinero, u.fechaAlta, u.activo) " +
            "FROM Usuario u WHERE u.id = :id")
    Optional<UsuarioResponseDTO> buscarPorId(@Param(":id") Long id);

    //obtenerUsuariosPorTipo
    @Query("SELECT new com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO.UsuarioResponseDTO(" +
            "u.nombre, u.apellido, u.email, u.telefono, u.tipoUsuario, u.nombreUsuario, u.dinero, u.fechaAlta, u.activo) " +
            "FROM Usuario u WHERE u.tipoUsuario = :tipoUsuario")
    List<UsuarioResponseDTO> obtenerUsuariosPorTipo(@Param(":tipoUsuario") String tipoUsuario);

}