package com.integrador.grupoA.controllers;

import com.integrador.grupoA.services.CuentaService;
import com.integrador.grupoA.services.dto.cuentas.cuentaRequest.CuentaRequestDTO;
import com.integrador.grupoA.services.dto.cuentas.cuentaResponse.CuentaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/cuentas")
public class CuentaController {

    @Autowired
    CuentaService cuentaService;

    @GetMapping("")
    public ResponseEntity<List<CuentaResponseDTO>> findAllCuentas(){
        return ResponseEntity.ok(cuentaService.findAllCuentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaResponseDTO> findCuentaById(@PathVariable Long id){
        return ResponseEntity.ok(cuentaService.findCuentaById(id));
    }

    @GetMapping("/entidad_bancaria/{entidad_bancaria}")
    public ResponseEntity<List<CuentaResponseDTO>> findCuentasByEntidadBancaria(@PathVariable String entidad_bancaria){
        return ResponseEntity.ok(cuentaService.findCuentasByEntidadBancaria(entidad_bancaria));
    }

    @GetMapping("/numero_cuenta/{numero_cuenta}")
    public ResponseEntity<CuentaResponseDTO> findCuentaByNumeroCuenta(@PathVariable int numero_cuenta){
        return ResponseEntity.ok(cuentaService.findCuentaByNumeroCuenta(numero_cuenta));
    }

    @GetMapping("id_titular/{id_titular}")
    public ResponseEntity<CuentaResponseDTO> findCuentaByTitular(@PathVariable String id_titular){
        return ResponseEntity.ok(cuentaService.findCuentaByTitular(id_titular));
    }

    @PostMapping("")
    public ResponseEntity<CuentaResponseDTO> agregarCuenta(@RequestBody CuentaRequestDTO request){
        CuentaResponseDTO cuenta = cuentaService.agregarCuenta(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cuenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CuentaResponseDTO> borrarCuenta(@PathVariable Long id){
        CuentaResponseDTO cuentaBorrada = cuentaService.borrarCuenta(id);
        return ResponseEntity.ok(cuentaBorrada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaResponseDTO> modificarCuenta(@PathVariable Long id, @RequestBody CuentaRequestDTO request){
        return ResponseEntity.ok(cuentaService.modificarCuenta(id, request));
    }

    @PostMapping("/{id}/verificar-saldo")
    public ResponseEntity<CuentaResponseDTO> verificarYActualizarSaldo(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        return ResponseEntity.ok(cuentaService.verificarYDescontarSaldo(id, request.get("saldo")));
    }


}
