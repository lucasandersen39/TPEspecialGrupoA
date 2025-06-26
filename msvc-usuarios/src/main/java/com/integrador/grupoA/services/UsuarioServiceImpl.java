package com.integrador.grupoA.services;

import com.integrador.grupoA.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.integrador.grupoA.repositories.UsuarioRepository;
import com.integrador.grupoA.services.dto.usuario.usuarioRequestDTO.UsuarioRequestDTO;
import com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO.UsuarioResponseDTO;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    //Devuelve Usuario, deberia devolver UsuarioResponseDTO
    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listar() {
        return usuarioRepository.traerUsuariosDTO();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioResponseDTO> buscarPorId(Long idUsuario) {
        return usuarioRepository.buscarPorId(idUsuario);
    }

    @Override
    @Transactional
    public Optional<UsuarioResponseDTO> crearUsuario(UsuarioRequestDTO usuario) {
        Optional<UsuarioResponseDTO> o = usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario());
        Optional<UsuarioResponseDTO> p = usuarioRepository.findByEmail(usuario.getEmail());

        if (o.isEmpty() && p.isEmpty()) {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(usuario.getNombre());
            nuevoUsuario.setApellido(usuario.getApellido());
            nuevoUsuario.setEmail(usuario.getEmail());
            nuevoUsuario.setTelefono(usuario.getTelefono());
            nuevoUsuario.setNombreUsuario(usuario.getNombreUsuario());
            nuevoUsuario.setTipoUsuario("Basico"); //Posibilidad de armar 2 funciones para crear cada tipo de usuario dependiendo la funcionalidad
            nuevoUsuario.setDinero(0.0);
            nuevoUsuario.setFechaAlta(LocalDateTime.now());
            nuevoUsuario.setActivo(true);
            usuarioRepository.save(nuevoUsuario);
            return Optional.of(new UsuarioResponseDTO(nuevoUsuario.getNombre(), nuevoUsuario.getApellido(), nuevoUsuario.getEmail(), nuevoUsuario.getTelefono(),
                    nuevoUsuario.getTipoUsuario(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getDinero(), nuevoUsuario.getFechaAlta(),
                    nuevoUsuario.isActivo()));
        } else {
            System.err.println("Error: Ya existe un usuario con ese nombre o email.");
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<UsuarioResponseDTO> modificarUsuario(UsuarioRequestDTO usuario, Long id) {
        Optional<Usuario> o = usuarioRepository.findById(id);
        if (o.isPresent()) {
            Usuario usuarioExistente = o.get();
            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setApellido(usuario.getApellido());
            usuarioExistente.setTelefono(usuario.getTelefono());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioRepository.save(usuarioExistente);
            return Optional.of(new UsuarioResponseDTO(usuarioExistente.getNombre(), usuarioExistente.getApellido(), usuarioExistente.getEmail(), usuarioExistente.getTelefono(),
                    usuarioExistente.getTipoUsuario(), usuarioExistente.getNombreUsuario(), usuarioExistente.getDinero(), usuarioExistente.getFechaAlta(),
                    usuarioExistente.isActivo()));
        }
        else {
            System.err.println("Error: No existe un usuario con ese ID");
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void eliminarUsuario(Long id) {
        boolean existe = usuarioRepository.existsById(id);
        if (existe) {
            usuarioRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Usuario con ID " + id + " no encontrado."
            );
        }
    }

    @Override
    @Transactional
    //esta funcionalidad se usa para borrado logico y activacion de usuarios
    public void cambiarActivoUsuario(Long id) {
        //controlar lo que muestra si no llega ningun usuario con ese id
        Optional<Usuario> o = usuarioRepository.findById(id);
        if (o.isPresent()) {
            Usuario usuario = o.get();
            usuario.setActivo(!usuario.isActivo());
            usuarioRepository.save(usuario);
        }
        else {
            System.err.println("Error: No existe un usuario con ese ID");
        }
    }

    @Override
    @Transactional
    public List<UsuarioResponseDTO> obtenerUsuariosPorTipo(String tipo){
        //controlar el hecho de poner un tipo de usuario que no exista
        return usuarioRepository.obtenerUsuariosPorTipo(tipo);
    }

    @Transactional
    @Override
    public Optional<UsuarioResponseDTO> buscarPorNombreUsuario(String nombreUsuario) {
        return usuarioRepository.buscarPorNombreUsuario(nombreUsuario);
    }
}
