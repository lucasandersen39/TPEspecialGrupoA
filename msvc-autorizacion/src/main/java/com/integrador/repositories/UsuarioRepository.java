package com.integrador.repositories;

import java.util.Optional;

import com.integrador.entites.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(final String username);
    boolean existsByUsername(final String username);
    boolean existsByIdUsuario(final Long idUsuario);
}