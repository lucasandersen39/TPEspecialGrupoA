package com.integrador.services;

import java.util.Set;
import java.util.stream.Collectors;

import com.integrador.dto.RegisterRequestDTO;
import com.integrador.entites.Rol;
import com.integrador.entites.Usuario;
import com.integrador.enums.RolEnum;
import com.integrador.repositories.RolRepository;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RolRepository rolRepository;

    public Usuario register(final RegisterRequestDTO request) {
        final String hashedPassword = passwordEncoder.encode(request.getPassword());
        final Set<Rol> roles = request.getRoles().stream().map(s -> rolRepository.findByRole(RolEnum.valueOf(s.toUpperCase()))
                .orElse(new Rol(RolEnum.valueOf(s.toUpperCase())))).collect(Collectors.toSet());
        final Usuario usuario = Usuario.builder().username(request.getUsername()).password(hashedPassword)
                .roles(roles).idUsuario(request.getIdUsuario()).build();
        try {
            usuarioService.save(usuario);
        } catch (PropertyValueException e) {
            return null;
        }
        return usuario;
    }
}