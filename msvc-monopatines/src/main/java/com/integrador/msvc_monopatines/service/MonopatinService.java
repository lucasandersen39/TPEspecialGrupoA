package com.integrador.msvc_monopatines.service;

import com.integrador.msvc_monopatines.domain.Monopatin;
import com.integrador.msvc_monopatines.repository.MonopatinRepository;
import com.integrador.msvc_monopatines.service.dto.request.MonopatinRequestDTO;
import com.integrador.msvc_monopatines.service.dto.response.MonopatinResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MonopatinService {
    private final MonopatinRepository monopatinRepository;

    public MonopatinResponseDTO saveMonopatin(MonopatinRequestDTO dto) {
        Monopatin monopatin = new Monopatin(null, dto.getEstado(), dto.getCoordenadas(),
                dto.getFechaInicioPausa(), dto.getKmRecorridos(), dto.getTiempoUsado());
        Monopatin saved = monopatinRepository.save(monopatin);
        return new MonopatinResponseDTO(saved);
    }

    public List<MonopatinResponseDTO> getAllMonopatines() {
        return monopatinRepository.findAll().stream().map(monopatin ->
                        new MonopatinResponseDTO(monopatin)).collect(Collectors.toList());
    }

    public MonopatinResponseDTO getMonopatinById(String id) {
        return monopatinRepository.findById(id)
                .map(monopatin -> new MonopatinResponseDTO(monopatin))
                .orElseThrow(() -> new RuntimeException("Monopatín no encontrado"));
    }

    public MonopatinResponseDTO updateMonopatin(String id, MonopatinRequestDTO dto) {
        Monopatin monopatin = monopatinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Monopatín no encontrado"));

        monopatin.setEstado(dto.getEstado());
        monopatin.setCoordenadas(dto.getCoordenadas());
        monopatin.setFechaInicioPausa(dto.getFechaInicioPausa());
        monopatin.setKmRecorridos(dto.getKmRecorridos());
        monopatin.setTiempoUsado(dto.getTiempoUsado());

        Monopatin updated = monopatinRepository.save(monopatin);
        return new MonopatinResponseDTO(updated);
    }

    public boolean deleteMonopatin(String id) {
        if (!monopatinRepository.existsById(id)) {
            return false; // Si no existe el monopatín, retornamos false.
        }
        monopatinRepository.deleteById(id);
        return true; // Si se eliminó correctamente, retornamos true.
    }


}

