package com.integrador.grupoA.controllers;

import com.integrador.grupoA.services.ParadaService;
import com.integrador.grupoA.dto.MonopatinResponseDTO;
import com.integrador.grupoA.dto.ParadaRequestDTO;
import com.integrador.grupoA.dto.ParadaResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/parada")
public class ParadaController {

    @Autowired
    private ParadaService paradaService;

    @GetMapping("")
    public List<ParadaResponseDTO> listar(){return paradaService.listar();}

    @GetMapping("/{id}")
    public Optional<ParadaResponseDTO> buscarPorId(@PathVariable Long id){return paradaService.buscarPorId(id);}

    @GetMapping("/coordenadas")
    public Optional<ParadaResponseDTO> buscarPorCoordenada(@RequestParam  Double x, @RequestParam  Double y){return paradaService.buscarPorCoordenada(x, y);}

    @PostMapping("")
    public ResponseEntity<Optional<ParadaResponseDTO>> crearParada(@RequestBody @Valid ParadaRequestDTO parada){
        final Optional<ParadaResponseDTO> result =paradaService.crearParada(parada);
        return ResponseEntity.accepted().body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<ParadaResponseDTO>> modificarParada(@RequestBody @Valid ParadaRequestDTO parada, @PathVariable Long id){
        final Optional<ParadaResponseDTO> result = paradaService.modificarParada(parada, id);
        return ResponseEntity.accepted().body(result);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarParada(@PathVariable Long id){paradaService.eliminarParada(id);}

    @GetMapping("/monopatinesCercanos")
    public List<MonopatinResponseDTO> monopatinesCercanos(@RequestParam  Double x, @RequestParam  Double y){return paradaService.buscarMonopatinesCercanos(x, y);}

    @GetMapping("/id_valido/{id}")
    public ResponseEntity<Void> validarParada(@PathVariable Long id) {
        boolean existe = paradaService.validarParada(id);
        return existe ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
