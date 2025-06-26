package com.integrador.msvc_monopatines.service;

import com.integrador.msvc_monopatines.domain.Monopatin;
import com.integrador.msvc_monopatines.feignClients.ViajeFeignClient;
import com.integrador.msvc_monopatines.repository.MonopatinRepository;
import com.integrador.msvc_monopatines.service.dto.request.MonopatinRequestDTO;
import com.integrador.msvc_monopatines.service.dto.response.MonoParaParadaResponseDTO;
import com.integrador.msvc_monopatines.service.dto.response.MonopatinResponseDTO;
import com.integrador.msvc_monopatines.feignClients.ParadaFeignClient;
import com.integrador.msvc_monopatines.service.dto.response.ReporteUsoDTO;
import com.integrador.msvc_monopatines.service.exception.ParadaInvalidaException;
import com.integrador.msvc_monopatines.service.exception.RecursoNoEncontradoException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MonopatinService {
    private final MonopatinRepository monopatinRepository;

    @Autowired
    private ParadaFeignClient paradaFeignClient;

    @Autowired
    private ViajeFeignClient viajeFeignClient;

    @Transactional
    public MonopatinResponseDTO saveMonopatin(MonopatinRequestDTO dto) {
        try {
            ResponseEntity<Void> respuesta = paradaFeignClient.validarParada(dto.getIdParada());

            if (respuesta.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ParadaInvalidaException("La parada con ID " + dto.getIdParada() + " no existe.");
            }

        } catch (FeignException.NotFound e) {
            throw new ParadaInvalidaException("La parada con ID " + dto.getIdParada() + " no existe.");
        }

        Monopatin monopatin = new Monopatin(null, dto.getEstado(), dto.getIdParada(), dto.getKmRecorridos(), dto.getTiempoUsado());
        Monopatin saved = monopatinRepository.save(monopatin);
        return new MonopatinResponseDTO(saved);
    }

    @Transactional
    public List<MonopatinResponseDTO> getAllMonopatines() {
        return monopatinRepository.findAll().stream().map(monopatin ->
                new MonopatinResponseDTO(monopatin)).collect(Collectors.toList());
    }

    @Transactional
    public MonopatinResponseDTO getMonopatinById(String id) {
        return monopatinRepository.findById(id)
                .map(monopatin -> new MonopatinResponseDTO(monopatin))
                .orElseThrow(() -> new RuntimeException("Monopatín no encontrado"));
    }

    @Transactional
    public MonopatinResponseDTO updateMonopatin(String id, MonopatinRequestDTO dto) {
        try {
            ResponseEntity<Void> respuesta = paradaFeignClient.validarParada(dto.getIdParada());

            if (respuesta.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ParadaInvalidaException("La parada con ID " + dto.getIdParada() + " no existe.");
            }

        } catch (FeignException.NotFound e) {
            throw new ParadaInvalidaException("La parada con ID " + dto.getIdParada() + " no existe.");
        }

        Monopatin monopatin = monopatinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Monopatín con ID " + id + " no encontrado"));

        monopatin.setEstado(dto.getEstado());
        monopatin.setIdParada(dto.getIdParada());
        monopatin.setKmRecorridos(dto.getKmRecorridos());
        monopatin.setTiempoUsado(dto.getTiempoUsado());

        Monopatin updated = monopatinRepository.save(monopatin);
        return new MonopatinResponseDTO(updated);
    }

    @Transactional
    public void marcarComoEnMantenimiento(String idMonopatin) {
        Monopatin monopatin = monopatinRepository.findById(idMonopatin)
                .orElseThrow(() -> new RecursoNoEncontradoException("Monopatín no encontrado con ID: " + idMonopatin));

        monopatin.setEstado(2); // Estado = 2 → mantenimiento
        monopatinRepository.save(monopatin);
    }

    @Transactional
    public boolean deleteMonopatin(String id) {
        if (!monopatinRepository.existsById(id)) {
            return false; // Si no existe el monopatín, retornamos false.
        }
        monopatinRepository.deleteById(id);
        return true; // Si se eliminó correctamente, retornamos true.
    }

    @Transactional
    public List<MonoParaParadaResponseDTO> obtenerDisponibles() {
        List<Monopatin> disponibles = monopatinRepository.findByEstado(0);
        return disponibles.stream()
                .map(MonoParaParadaResponseDTO::new)
                .toList();
    }

    @Transactional
    public List<ReporteUsoDTO> generarReporteUso(boolean incluirPausa) {
        List<Monopatin> monopatines = monopatinRepository.findAll();

        Map<String, Double> pausasPorMonopatin;
        if (incluirPausa) {
            pausasPorMonopatin = viajeFeignClient.obtenerTiempoPausadoTotal(); // suponiendo que devuelve Map<String, Double>
        } else {
            pausasPorMonopatin = new HashMap<>();
        }

        return monopatines.stream()
                .map(m -> {
                    ReporteUsoDTO dto = new ReporteUsoDTO();
                    dto.setIdMonopatin(m.getIdMonopatin());
                    dto.setKmRecorridos(m.getKmRecorridos());
                    if (incluirPausa) {
                        dto.setTiempoTotalPausado(pausasPorMonopatin.getOrDefault(m.getIdMonopatin(), 0.0));
                    }
                    return dto;
                })
                .toList();
    }

//    @Transactional
//    public void sumarKilometros(String id, double km) {
//        Optional<Monopatin> monopatin = monopatinRepository.findById(id);
//        if (monopatin.isPresent()) {
//            monopatin.get().setKmRecorridos(monopatin.get().getKmRecorridos() + km);
//            monopatinRepository.save(monopatin.get());
//        }
//    }
@Transactional
public void sumarKilometros(String id, double km) {
    try {
        Monopatin monopatin = monopatinRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Monopatín no encontrado con id: " + id));

        monopatin.setKmRecorridos(monopatin.getKmRecorridos() + km);
        monopatinRepository.save(monopatin);
    } catch (Exception e) {
        // Registra el error para depuración
        throw new IllegalArgumentException("Error al sumar kilómetros al monopatín con id {}: {}");
    }
}

    @Transactional
    public void sumarTiempoUso(String id, double tiempo) {
        Optional<Monopatin> monopatin = monopatinRepository.findById(id);
        if (monopatin.isPresent()) {
            double tiempoActualUso = monopatin.get().getTiempoUsado();
            monopatin.get().setTiempoUsado(tiempoActualUso + tiempo);
            monopatinRepository.save(monopatin.get());
        }
    }
}

