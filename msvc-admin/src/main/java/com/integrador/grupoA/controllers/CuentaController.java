package com.integrador.grupoA.controllers;

import com.integrador.grupoA.services.CuentaService;
import com.integrador.grupoA.services.dto.cuentas.cuentaRequest.CuentaRequestDTO;
import com.integrador.grupoA.services.dto.cuentas.cuentaResponse.CuentaResponseDTO;
import com.integrador.grupoA.swagger.SwaggerErrorResponsesCuentas;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/cuentas")
@Tag(name = "Cuentas", description = "Gestión de cuentas bancarias")
public class CuentaController implements SwaggerErrorResponsesCuentas {

    @Autowired
    CuentaService cuentaService;

    @Operation(summary = "Obtener una lista de todas las cuentas disponibles")
    @GetMapping("")
    public ResponseEntity<List<CuentaResponseDTO>> findAllCuentas(){
        return ResponseEntity.ok(cuentaService.findAllCuentas());
    }

    @Operation(summary = "Obtener una cuenta por su id")
    @GetMapping("/{id}")
    public ResponseEntity<CuentaResponseDTO> findCuentaById(@PathVariable Long id){
        return ResponseEntity.ok(cuentaService.findCuentaById(id));
    }

    @Operation(summary = "Obtener una lista de todas las cuentas por su entidad bancaria")
    @GetMapping("/entidad_bancaria/{entidad_bancaria}")
    public ResponseEntity<List<CuentaResponseDTO>> findCuentasByEntidadBancaria(@PathVariable String entidad_bancaria){
        return ResponseEntity.ok(cuentaService.findCuentasByEntidadBancaria(entidad_bancaria));
    }

    @Operation(summary = "Obtener una cuenta por su número de cuenta")
    @GetMapping("/numero_cuenta/{numero_cuenta}")
    public ResponseEntity<CuentaResponseDTO> findCuentaByNumeroCuenta(@PathVariable int numero_cuenta){
        return ResponseEntity.ok(cuentaService.findCuentaByNumeroCuenta(numero_cuenta));
    }

    @Operation(summary = "Obtener una cuenta por el id del titular")
    @GetMapping("id_titular/{id_titular}")
    public ResponseEntity<CuentaResponseDTO> findCuentaByTitular(@PathVariable String id_titular){
        return ResponseEntity.ok(cuentaService.findCuentaByTitular(id_titular));
    }

    @Operation(summary = "Agregar una nueva cuenta")
    @PostMapping("")
    public ResponseEntity<CuentaResponseDTO> agregarCuenta(@RequestBody CuentaRequestDTO request){
        CuentaResponseDTO cuenta = cuentaService.agregarCuenta(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cuenta);
    }

    @Operation(summary = "Borrar una cuenta existente")
    @DeleteMapping("/{id}")
    public ResponseEntity<CuentaResponseDTO> borrarCuenta(@PathVariable Long id){
        CuentaResponseDTO cuentaBorrada = cuentaService.borrarCuenta(id);
        return ResponseEntity.ok(cuentaBorrada);
    }

    @Operation(summary = "Modificar una cuenta existente")
    @PutMapping("/{id}")
    public ResponseEntity<CuentaResponseDTO> modificarCuenta(@PathVariable Long id, @RequestBody CuentaRequestDTO request){
        return ResponseEntity.ok(cuentaService.modificarCuenta(id, request));
    }

    @Operation(summary = "Realizar cobro")
    @PostMapping("/{id}/verificar-saldo")
    public ResponseEntity<CuentaResponseDTO> verificarYActualizarSaldo(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        return ResponseEntity.ok(cuentaService.verificarYDescontarSaldo(id, request.get("saldo")));
    }

}
