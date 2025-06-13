package com.integrador.grupoA.services;

import com.integrador.grupoA.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.integrador.grupoA.repositories.UsuarioRepository;
import com.integrador.grupoA.services.dto.usuario.usuarioRequestDTO.UsuarioRequestDTO;
import com.integrador.grupoA.services.dto.usuario.usuarioResponseDTO.UsuarioResponseDTO;

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

        if (!o.isPresent() && !p.isPresent()) {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(usuario.getNombre());
            nuevoUsuario.setApellido(usuario.getApellido());
            nuevoUsuario.setEmail(usuario.getEmail());
            nuevoUsuario.setTelefono(usuario.getTelefono());
            nuevoUsuario.setNombreUsuario(usuario.getNombreUsuario());
            nuevoUsuario.setTipoUsuario("Basico"); //Posibilidad de armar 2 funciones para crear cada tipo de usuario dependiendo la funcionalidad
            nuevoUsuario.setDinero(0.0);
            nuevoUsuario.setFechaAlta(LocalDateTime.now());
            nuevoUsuario.setX(usuario.getX());
            nuevoUsuario.setY(usuario.getY());
            nuevoUsuario.setEstado(true);
            usuarioRepository.save(nuevoUsuario);
            return Optional.of(new UsuarioResponseDTO(nuevoUsuario.getNombre(), nuevoUsuario.getApellido(), nuevoUsuario.getEmail(), nuevoUsuario.getTelefono(),
                    nuevoUsuario.getTipoUsuario(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getX(), nuevoUsuario.getY(), nuevoUsuario.getDinero(),
                    nuevoUsuario.getFechaAlta(), nuevoUsuario.isEstado()));
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
            usuarioExistente.setX(usuario.getX());
            usuarioExistente.setY(usuario.getY());
            usuarioRepository.save(usuarioExistente);
            return Optional.of(new UsuarioResponseDTO(usuarioExistente.getNombre(), usuarioExistente.getApellido(), usuarioExistente.getEmail(), usuarioExistente.getTelefono(),
                    usuarioExistente.getTipoUsuario(), usuarioExistente.getNombreUsuario(), usuarioExistente.getX(), usuarioExistente.getY(), usuarioExistente.getDinero(),
                    usuarioExistente.getFechaAlta(), usuarioExistente.isEstado()));
        }
        else {
            System.err.println("Error: No existe un usuario con ese ID");
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void eliminarUsuario(Long id) {
        Optional<Usuario> o = usuarioRepository.findById(id);
        if (o.isPresent()) {
            usuarioRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public void cambiarEstadoUsuario(Long id) {
        Optional<Usuario> o = usuarioRepository.findById(id);
        if (o.isPresent()) {
            Usuario usuario = o.get();
            usuario.setEstado(!usuario.isEstado());
            usuarioRepository.save(usuario);
        }
        else {
            System.err.println("Error: No existe un usuario con ese ID");
        }
    }
}
