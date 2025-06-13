package com.integrador.grupoA.controllers;

import com.integrador.grupoA.entities.Tarifa;
import com.integrador.grupoA.exceptions.custom.BusinessValidationException;
import com.integrador.grupoA.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/admin/tarifas")
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * El manejador global de excepciones me permite capturar las excepciones que se producen en el controlador
     * y las devuelve en un formato JSON.
     * Por ese motivo ya no utilizo try -catch
     */

    // Ajusta los precios de las tarifas y los habilita a partir de cierta fecha.
    //Ver como trabajar lo de la fecha
    @PutMapping("/actualizar-precios")
    public ResponseEntity<Tarifa> actualizarPrecios(@Valid @RequestBody Tarifa request, @RequestParam LocalDate fecha) {
        if (fecha.isBefore(LocalDate.now()))
            throw new BusinessValidationException("La fecha de vigencia debe ser igual o posterior a hoy");

        Tarifa tarifaActualizada = adminService.actualizarPrecios(request.getId(), request.getMonto());
        return ResponseEntity.ok(tarifaActualizada);
    }

    // Da de alta una nueva tarifa
    @PostMapping("")
    public ResponseEntity<Tarifa> agregarTarifa(@Valid @RequestBody Tarifa request) {
        Tarifa tarifa = adminService.agregarTarifa(request);
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
    public ResponseEntity<Tarifa> modificarTarifa(@PathVariable Long id, @Valid @RequestBody Tarifa request) {
        return ResponseEntity.ok(adminService.modificarTarifa(id, request));
    }
}
