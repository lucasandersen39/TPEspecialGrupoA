package com.integrador.grupoA.controllers;

import com.integrador.grupoA.exceptions.custom.BusinessValidationException;
import com.integrador.grupoA.services.AdminService;
import com.integrador.grupoA.services.dto.tarifas.tarifaRequest.TarifaRequestDTO;
import com.integrador.grupoA.services.dto.tarifas.tarifaResponse.TarifaResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class AdminController {

    @Autowired
    AdminService adminService;

    // Obtener todas las tarifas
    @GetMapping("")
    public ResponseEntity<List<TarifaResponseDTO>> obtenerTarifas(){
        return ResponseEntity.ok(adminService.obtenerTarifas());
    }

    // Obtener una tarifa por su id
    @GetMapping("/{id}")
    public ResponseEntity<TarifaResponseDTO> obtenerTarifaPorId(@PathVariable Long id){
        return ResponseEntity.ok(adminService.findTarifaPorId(id));
    }

    // Obtener una tarifa por su tipo
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<TarifaResponseDTO> obtenerTarifaPorTipo(@PathVariable String tipo){
        return ResponseEntity.ok(adminService.obtenerTarifaPorTipo(tipo));
    }

    // Da de alta una nueva tarifa
    @PostMapping("")
    public ResponseEntity<TarifaResponseDTO> agregarTarifa(@Valid @RequestBody TarifaRequestDTO request) {
        TarifaResponseDTO tarifa = adminService.agregarTarifa(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarifa);
    }

    // Elimina una tarifa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarTarifa(@PathVariable Long id) {
        adminService.borrarTarifa(id);
        return ResponseEntity.noContent().build();
    }

    // Modifica una tarifa
    @PutMapping("/{id}")
    public ResponseEntity<TarifaResponseDTO> modificarTarifa(@PathVariable Long id, @Valid @RequestBody TarifaRequestDTO request) {
        return ResponseEntity.ok(adminService.modificarTarifa(id, request));
    }

    // Ajusta los precios de las tarifas y los habilita a partir de cierta fecha.
    //Ver como trabajar lo de la fecha
    @PutMapping("/actualizar-precios/{id}")
    public ResponseEntity<TarifaResponseDTO> actualizarPrecios(@PathVariable Long id, @Valid @RequestBody TarifaRequestDTO request, @RequestParam LocalDate fecha) {
        if (fecha.isBefore(LocalDate.now()))
            throw new BusinessValidationException("La fecha de vigencia debe ser igual o posterior a hoy");

        TarifaResponseDTO tarifaActualizada = adminService.actualizarPrecios(id, request.getMonto());
        return ResponseEntity.ok(tarifaActualizada);
    }
}
