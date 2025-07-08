package com.integrador.services;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.integrador.clients.UsuarioFeignClient;
import com.integrador.dto.RegisterRequestDTO;
import com.integrador.dto.RegisterResponseDTO;
import com.integrador.dto.UsuarioRequestDTO;
import com.integrador.dto.UsuarioResponseDTO;
import com.integrador.entites.Rol;
import com.integrador.entites.Usuario;
import com.integrador.enums.RolEnum;
import com.integrador.repositories.RolRepository;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private UsuarioFeignClient usuarioFeignClient;

    public RegisterResponseDTO register(final RegisterRequestDTO request) {
        final Optional<Usuario> usuarioUsername = usuarioService.findByUsername(request.getUsername());
        if (usuarioUsername != null && usuarioUsername.isPresent()) {
            throw new RuntimeException("El username ya fue registrado previamente");
        }

        final UsuarioRequestDTO usuarioRequest = new UsuarioRequestDTO(request.getNombre(), request.getApellido(), request.getEmail(), request.getTelefono(), request.getUsername());
        final ResponseEntity<Optional<UsuarioResponseDTO>> reponseUsuario;
        try {
            reponseUsuario = usuarioFeignClient.crearUsuario(usuarioRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error al crear usuario usuarios", e);
        }

        final Optional<UsuarioResponseDTO> usuarioOpt = reponseUsuario.getBody();
        if (usuarioOpt != null && usuarioOpt.isPresent()) {
            final String hashedPassword = passwordEncoder.encode(request.getPassword());
            final Set<Rol> roles = request.getRoles().stream().map(s -> rolRepository.findByRole(RolEnum.valueOf(s.toUpperCase()))
                    .orElse(new Rol(RolEnum.valueOf(s.toUpperCase())))).collect(Collectors.toSet());
            final Usuario usuario = Usuario.builder().username(request.getUsername()).password(hashedPassword)
                    .roles(roles).idUsuario(usuarioOpt.get().id()).build();
            try {
                usuarioService.save(usuario);
            } catch (PropertyValueException e) {
                return null;
            }
            return new RegisterResponseDTO(
                    request.getNombre(),
                    request.getApellido(),
                    request.getEmail(),
                    request.getTelefono(),
                    request.getUsername(),
                    request.getRoles()
            );
        }
        throw new RuntimeException("No se pudo crear el usuario.");
    }
}