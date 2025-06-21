package com.integrador.grupoA.services;

import com.integrador.grupoA.entities.Parada;
import com.integrador.grupoA.repositories.ParadaRepository;
import com.integrador.grupoA.dto.MonopatinResponseDTO;
import com.integrador.grupoA.dto.ParadaRequestDTO;
import com.integrador.grupoA.dto.ParadaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ParadaServiceImpl implements ParadaService{

    @Autowired
    private ParadaRepository paradaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ParadaResponseDTO> listar() {
        return paradaRepository.findAll().stream().map(ParadaResponseDTO::toParadaResponseDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParadaResponseDTO> buscarPorId(Long id) {
        return paradaRepository.findById(id).map(ParadaResponseDTO::toParadaResponseDTO);
    }

    @Override
    @Transactional
    public Optional<ParadaResponseDTO> crearParada(ParadaRequestDTO parada) {
        try {
            Parada p = new Parada();
            p.setX(parada.getX());
            p.setY(parada.getY());
            p.setNombre(parada.getNombre());

            Parada guardada = paradaRepository.save(p);
            return Optional.of(new ParadaResponseDTO(guardada.getNombre(), guardada.getX(), guardada.getY()));

        } catch (DataIntegrityViolationException e) {
            System.err.println("Error: Ya existe una parada con esas coordenadas (x, y).");
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
    @Transactional
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

    @Override
    @Transactional(readOnly = true)
    public Optional<ParadaResponseDTO> buscarPorCoordenada(Double x, Double y) {
        return paradaRepository.buscarPorCoordenadas(x,y).map(ParadaResponseDTO::toParadaResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MonopatinResponseDTO> buscarMonopatinesCercanos(Double x, Double y) {
        List<Parada>lp = paradaRepository.findAll();

        lp.sort(Comparator.comparingDouble(p -> Math.abs(p.getX() - x) + Math.abs(p.getY() - y)));

        for (Parada p : lp){
            // Consulta a Monopatines con p.getId
            // if(!List<Monopatines>.isEmpty())  return List<Monopatines>.map(MonopatinResponseDTO::toMonopatinResponseDTO);
        }

        return List.of();
    }

    @Override
    public boolean validarParada(Long id) {
        Optional<Parada> p = paradaRepository.findById(id);
        return p.isPresent();
    }
}
