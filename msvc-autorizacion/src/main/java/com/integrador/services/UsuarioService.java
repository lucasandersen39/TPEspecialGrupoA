package com.integrador.services;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.integrador.entites.Rol;
import com.integrador.entites.Usuario;
import com.integrador.repositories.RolRepository;
import com.integrador.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;

    public void save(final Usuario usuario) {
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new DataIntegrityViolationException("El username '" + usuario.getUsername() + "' ya está en uso");
        }

        if (usuarioRepository.existsByIdUsuario(usuario.getIdUsuario())) {
            throw new DataIntegrityViolationException("El idUsuario '" + usuario.getIdUsuario() + "' ya está en uso");
        }
        final Set<Rol> rolesToAdd = usuario.getRoles().stream().filter(r -> r.getId() == null).collect(Collectors.toSet());
        if (!rolesToAdd.isEmpty()) {
            rolRepository.saveAll(rolesToAdd);
        }
        usuarioRepository.save(usuario);
    }

    public Optional<Usuario> findByUsername(final String username) {
        return usuarioRepository.findByUsername(username);
    }
}