package com.integrador.grupoA.controllers;

import com.integrador.grupoA.exceptions.custom.BusinessValidationException;
import com.integrador.grupoA.services.TarifaService;
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
public class TarifaController {

    @Autowired
    TarifaService tarifaService;

    // Faltaria agregar el metodo que te devuelva la tarifa que tenga el monto actual.

    // Obtener todas las tarifas
    @GetMapping("")
    public ResponseEntity<List<TarifaResponseDTO>> obtenerTarifas(){
        return ResponseEntity.ok(tarifaService.obtenerTarifas());
    }

    // Obtener una tarifa por su id
    @GetMapping("/{id}")
    public ResponseEntity<TarifaResponseDTO> obtenerTarifaPorId(@PathVariable Long id){
        return ResponseEntity.ok(tarifaService.findTarifaPorId(id));
    }

    // Obtener la tarifa vigente por tipo de tarifa
    @GetMapping("/vigente/{tipo}")
    public ResponseEntity<TarifaResponseDTO> obtenerTarifaVigentePorTipo(@PathVariable String tipo){
        return ResponseEntity.ok(tarifaService.obtenerTarifaVigentePorTipo(tipo));
    }

    // Obtener una tarifa por su tipo
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<TarifaResponseDTO>> obtenerTarifasPorTipo(@PathVariable String tipo){
        return ResponseEntity.ok(tarifaService.obtenerTarifasPorTipo(tipo));
    }

    // Da de alta una nueva tarifa
    @PostMapping("")
    public ResponseEntity<TarifaResponseDTO> agregarTarifa(@Valid @RequestBody TarifaRequestDTO request) {
        TarifaResponseDTO tarifa = tarifaService.agregarTarifa(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarifa);
    }

    // Elimina una tarifa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarTarifa(@PathVariable Long id) {
        tarifaService.borrarTarifa(id);
        return ResponseEntity.noContent().build();
    }

    // Modifica una tarifa
    @PutMapping("/{id}")
    public ResponseEntity<TarifaResponseDTO> modificarTarifa(@PathVariable Long id, @Valid @RequestBody TarifaRequestDTO request) {
        return ResponseEntity.ok(tarifaService.modificarTarifa(id, request));
    }

    // Ajusta los precios de las tarifas y los habilita a partir de cierta fecha.
    //Ver como trabajar lo de la fecha
    @PutMapping("/actualizar-precios/{id}")
    public ResponseEntity<TarifaResponseDTO> actualizarPrecios(@PathVariable Long id, @Valid @RequestBody TarifaRequestDTO request, @RequestParam LocalDate fecha) {
        return ResponseEntity.ok(tarifaService.actualizarPrecios(id, request, fecha));
    }
}
