package com.integrador.msvc_monopatines.web;

import com.integrador.msvc_monopatines.domain.Monopatin;
import com.integrador.msvc_monopatines.repository.MonopatinRepository;
import com.integrador.msvc_monopatines.service.MonopatinService;
import com.integrador.msvc_monopatines.service.dto.request.MonopatinRequestDTO;
import com.integrador.msvc_monopatines.service.dto.response.MonoParaParadaResponseDTO;
import com.integrador.msvc_monopatines.service.dto.response.MonopatinResponseDTO;
import com.integrador.msvc_monopatines.service.dto.response.ReporteUsoDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/monopatin")
@RequiredArgsConstructor
public class MonopatinResource {
    private final MonopatinService monopatinService;
    private final MonopatinRepository monopatinRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<MonopatinResponseDTO> saveMonopatin(@Valid @RequestBody MonopatinRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(monopatinService.saveMonopatin(dto));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleParadaInvalida(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String mensaje = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Error de validación");
        return ResponseEntity.badRequest().body(mensaje);
    }


    @PreAuthorize("hasRole('USER')")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public List<MonopatinResponseDTO> getAllMonopatines() {
        return monopatinService.getAllMonopatines();
    }

    @PreAuthorize("hasRole('USER')")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<MonopatinResponseDTO> getMonopatinById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(monopatinService.getMonopatinById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<MonopatinResponseDTO> updateMonopatin(@PathVariable String id, @Valid @RequestBody MonopatinRequestDTO dto) {
        try {
            return ResponseEntity.ok(monopatinService.updateMonopatin(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //Poner en mantenimiento monopatin
    @PreAuthorize("hasRole('MANTENIMIENTO')")
    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping("/{idMonopatin}/mantenimiento")
    public ResponseEntity<String> ponerEnMantenimiento(@PathVariable String idMonopatin) {
        monopatinService.marcarComoEnMantenimiento(idMonopatin);
        return ResponseEntity.ok("Monopatín " + idMonopatin + " puesto en mantenimiento.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMonopatin(@PathVariable String id) {
        boolean deleted = monopatinService.deleteMonopatin(id);
        return deleted
                ? ResponseEntity.ok(true)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    //Devolver los monopatines inactivos (no alquilados, disponibles para uso, que tienen estado 0
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/disponibles")
    public ResponseEntity<List<MonoParaParadaResponseDTO>> getMonopatinesDisponibles() {
        List<MonoParaParadaResponseDTO> disponibles = monopatinService.obtenerDisponibles();
        return ResponseEntity.ok(disponibles);
    }

    //Crea el reporte de todos los monopatines por km recorridos y opcionalmente tiempo pausado.
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/reporte-uso")
    public ResponseEntity<List<ReporteUsoDTO>> generarReporteUsoDefault() {
        List<ReporteUsoDTO> reporte = monopatinService.generarReporteUso(false); // sin pausas
        return ResponseEntity.ok(reporte);
    }

    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/existe/{id}")
    public ResponseEntity<Boolean> existeMonopatin(@PathVariable("id") String idMonopatin) {
        boolean existe = monopatinRepository.existsById(idMonopatin);
        return ResponseEntity.ok(existe);
    }

    @PutMapping("sumarKm/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> sumarKilometros(@PathVariable String id, @RequestBody double km){
        monopatinService.sumarKilometros(id, km);
        return ResponseEntity.ok().build();
    }

    @PutMapping("sumarTiempoUsado/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> sumarTiempoUso(@PathVariable String id, @RequestBody double tiempo){
        monopatinService.sumarTiempoUso(id, tiempo);
        return ResponseEntity.ok().build();
    }
}
