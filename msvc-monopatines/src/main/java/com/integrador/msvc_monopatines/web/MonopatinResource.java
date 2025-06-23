package com.integrador.msvc_monopatines.web;

import com.integrador.msvc_monopatines.repository.MonopatinRepository;
import com.integrador.msvc_monopatines.service.MonopatinService;
import com.integrador.msvc_monopatines.service.dto.request.MonopatinRequestDTO;
import com.integrador.msvc_monopatines.service.dto.response.MonoParaParadaResponseDTO;
import com.integrador.msvc_monopatines.service.dto.response.MonopatinResponseDTO;
import com.integrador.msvc_monopatines.service.dto.response.ReporteUsoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monopatin")
@RequiredArgsConstructor
public class MonopatinResource {
    private final MonopatinService monopatinService;
    private final MonopatinRepository monopatinRepository;

    @PostMapping
    public ResponseEntity<MonopatinResponseDTO> saveMonopatin(@RequestBody MonopatinRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(monopatinService.saveMonopatin(dto));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleParadaInvalida(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @GetMapping
    public List<MonopatinResponseDTO> getAllMonopatines() {
        return monopatinService.getAllMonopatines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonopatinResponseDTO> getMonopatinById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(monopatinService.getMonopatinById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonopatinResponseDTO> updateMonopatin(@PathVariable String id, @RequestBody MonopatinRequestDTO dto) {
        try {
            return ResponseEntity.ok(monopatinService.updateMonopatin(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //Poner en mantenimiento monopatin
    @PatchMapping("/{idMonopatin}/mantenimiento")
    public ResponseEntity<String> ponerEnMantenimiento(@PathVariable String idMonopatin) {
        monopatinService.marcarComoEnMantenimiento(idMonopatin);
        return ResponseEntity.ok("Monopatín " + idMonopatin + " puesto en mantenimiento.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMonopatin(@PathVariable String id) {
        boolean deleted = monopatinService.deleteMonopatin(id);
        return deleted
                ? ResponseEntity.ok(true)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    //Devolver los monopatines inactivos (no alquilados, disponibles para uso, que tienen estado 0
    @GetMapping("/disponibles")
    public ResponseEntity<List<MonoParaParadaResponseDTO>> getMonopatinesDisponibles() {
        List<MonoParaParadaResponseDTO> disponibles = monopatinService.obtenerDisponibles();
        return ResponseEntity.ok(disponibles);
    }

    //Crea el reporte de todos los monopatines por km recorridos y opcionalmente tiempo pausado.
    @GetMapping("/reporte-uso")
    public ResponseEntity<List<ReporteUsoDTO>> generarReporteUsoDefault() {
        List<ReporteUsoDTO> reporte = monopatinService.generarReporteUso(false); // sin pausas
        return ResponseEntity.ok(reporte);
    }

    @GetMapping("/reporte-uso/{incluirPausa}")
    public ResponseEntity<List<ReporteUsoDTO>> generarReporteUsoExplicito(@PathVariable String incluirPausa) {
        if (!incluirPausa.equalsIgnoreCase("true") && !incluirPausa.equalsIgnoreCase("false")) {
            return ResponseEntity.badRequest().build();
        }

        boolean incluir = Boolean.parseBoolean(incluirPausa);
        List<ReporteUsoDTO> reporte = monopatinService.generarReporteUso(incluir);
        return ResponseEntity.ok(reporte);
    }

    //Verifica si el id recibido corresponde a un monopatin existente para mantener consistencia con tabla de Viajes
    @GetMapping("/existe/{id}")
    public ResponseEntity<Boolean> existeMonopatin(@PathVariable("id") String idMonopatin) {
        boolean existe = monopatinRepository.existsById(idMonopatin);
        return ResponseEntity.ok(existe);
    }


}

