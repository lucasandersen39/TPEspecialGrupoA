package com.integrador.grupoA.services;

import com.integrador.grupoA.entities.Parada;
import com.integrador.grupoA.repositories.ParadaRepository;
import com.integrador.grupoA.services.dto.parada.paradaRequestDTO.ParadaRequestDTO;
import com.integrador.grupoA.services.dto.parada.paradaResponseDTO.ParadaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ParadaServiceImpl implements ParadaService{

    @Autowired
    private ParadaRepository paradaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ParadaResponseDTO> listar() {
        return paradaRepository.traerParadas();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParadaResponseDTO> buscarPorId(Long id) {
        return paradaRepository.buscarPorId(id);
    }

    @Override
    @Transactional
    public Optional<ParadaResponseDTO> crearParada(ParadaRequestDTO parada) {
        Optional<ParadaResponseDTO> cord = paradaRepository.buscarPorCoordenadas(parada.getX(), parada.getY());

        if (cord.isEmpty()){
            Parada p = new Parada();
            p.setX(parada.getX());
            p.setY(parada.getY());
            p.setNombre(parada.getNombre());
            paradaRepository.save(p);
            return Optional.of(new ParadaResponseDTO(p.getNombre(),p.getX(),p.getY()));

        }else{
            System.err.println("Error: Ya existe una parada con esas coordenadas.");
            return Optional.empty();
        }

    }

    @Override
    @Transactional
    public Optional<ParadaResponseDTO> modificarParada(ParadaRequestDTO parada, Long id) {
        Optional<Parada> pExistente = paradaRepository.findById(id);
        if (pExistente.isPresent()){
            Parada p = pExistente.get();
            p.setNombre(parada.getNombre());
            p.setX(parada.getX());
            p.setY(parada.getY());
            paradaRepository.save(p);
            return Optional.of(new ParadaResponseDTO(p.getNombre(),p.getX(),p.getY()));
        }else{
            System.err.println("Error: No existe una parada con ese ID");
            return Optional.empty();
        }
    }

    @Override
    public void eliminarParada(Long id) {
        boolean existe = paradaRepository.existsById(id);
        if(existe){
            paradaRepository.deleteById(id);
        }else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Parada con ID " + id + " no encontrada."
            );
        }
    }
}
