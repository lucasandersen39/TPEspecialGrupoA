package com.integrador.grupoA.controllers;

import com.integrador.grupoA.exceptions.custom.BusinessValidationException;
import com.integrador.grupoA.services.TarifaService;
import com.integrador.grupoA.services.dto.tarifas.tarifaRequest.TarifaRequestDTO;
import com.integrador.grupoA.services.dto.tarifas.tarifaResponse.TarifaResponseDTO;
import com.integrador.grupoA.swagger.SwaggerErrorResponsesTarifas;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
/**
 * El manejador global de excepciones me permite capturar las excepciones que se producen en el controlador
 * y las devuelve en un formato JSON.
 * Por ese motivo ya no utilizo try -catch
 */

@RestController
@RequestMapping("/api/admin/tarifas")
@Tag(name = "Tarifas", description = "Gestión de tarifas")
public class TarifaController implements SwaggerErrorResponsesTarifas {

    @Autowired
    TarifaService tarifaService;
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Obtener todas las tarifas")
    @GetMapping("")
    public ResponseEntity<List<TarifaResponseDTO>> obtenerTarifas(){
        return ResponseEntity.ok(tarifaService.obtenerTarifas());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Obtener una tarifa por su id")
    @GetMapping("/{id}")
    public ResponseEntity<TarifaResponseDTO> obtenerTarifaPorId(@PathVariable Long id){
        return ResponseEntity.ok(tarifaService.findTarifaPorId(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Obtener la tarifa vigente según tipo de tarifa")
    @GetMapping("/vigente/{tipo}")
    public ResponseEntity<TarifaResponseDTO> obtenerTarifaVigentePorTipo(@PathVariable String tipo){
        return ResponseEntity.ok(tarifaService.obtenerTarifaVigentePorTipo(tipo));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Obtener una lista de todas las tarifas por su tipo")
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<TarifaResponseDTO>> obtenerTarifasPorTipo(@PathVariable String tipo){
        return ResponseEntity.ok(tarifaService.obtenerTarifasPorTipo(tipo));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Dar de alta una nueva tarifa")
    @PostMapping("")
    public ResponseEntity<TarifaResponseDTO> agregarTarifa(@Valid @RequestBody TarifaRequestDTO request) {
        TarifaResponseDTO tarifa = tarifaService.agregarTarifa(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarifa);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar una tarifa existente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarTarifa(@PathVariable Long id) {
        tarifaService.borrarTarifa(id);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Modificar una tarifa existente")
    @PutMapping("/{id}")
    public ResponseEntity<TarifaResponseDTO> modificarTarifa(@PathVariable Long id, @Valid @RequestBody TarifaRequestDTO request) {
        return ResponseEntity.ok(tarifaService.modificarTarifa(id, request));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Actualizar los precios de las tarifas con una fecha de vigencia.")
    @PutMapping("/actualizar-precios/{id}")
    public ResponseEntity<TarifaResponseDTO> actualizarPrecios(@PathVariable Long id, @Valid @RequestBody TarifaRequestDTO request, @RequestParam LocalDate fecha) {
        return ResponseEntity.ok(tarifaService.actualizarPrecios(id, request, fecha));
    }
}
