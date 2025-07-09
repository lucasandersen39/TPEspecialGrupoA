package com.integrador.grupoA.controllers;

import com.integrador.grupoA.services.TipoTarifaService;
import com.integrador.grupoA.services.dto.tipoTarifas.tipoTarifaRequest.TipoTarifaRequestDTO;
import com.integrador.grupoA.services.dto.tipoTarifas.tipoTarifaResponse.TipoTarifaResponseDTO;
import com.integrador.grupoA.swagger.SwaggerErrorResponsesTipoTarifas;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/tipo_tarifas")
@Tag(name = "TipoTarifas", description = "Gestión de tipos de tarifas")
public class TipoTarifaController implements SwaggerErrorResponsesTipoTarifas {

    @Autowired
    TipoTarifaService tipoTarifaService;

    @Operation(summary = "Obtener todas las tarifas")
    @GetMapping("")
    public ResponseEntity<List<TipoTarifaResponseDTO>> findAllTipoTarifas() {
        return ResponseEntity.ok(tipoTarifaService.findAllTipoTarifas());
    }

    @Operation(summary = "Obtener una tarifa por su id")
    @GetMapping("/{id}")
    public ResponseEntity<TipoTarifaResponseDTO> obtenerTarifaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tipoTarifaService.findTarifaPorId(id));
    }

    @Operation(summary = "Obtener una tarifa por su tipo")
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<TipoTarifaResponseDTO> findTarifaPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(tipoTarifaService.findTarifaPorTipo(tipo));
    }

    @Operation(summary = "Dar de alta una nueva tarifa")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("")
    public ResponseEntity<TipoTarifaResponseDTO> agregarTarifa(@Valid @RequestBody TipoTarifaRequestDTO request) {
        TipoTarifaResponseDTO tipo_tarifa = tipoTarifaService.crearTipoTarifa(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipo_tarifa);
    }

    @Operation(summary = "Elimina una tarifa existente")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarTarifa(@PathVariable Long id) {
        tipoTarifaService.eliminarTipoTarifa(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Modificar una tarifa existente")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<TipoTarifaResponseDTO> actualizarTipoTarifa(@PathVariable Long id, @Valid @RequestBody TipoTarifaRequestDTO request) {
        return ResponseEntity.ok(tipoTarifaService.actualizarTipoTarifa(id, request));
    }
}