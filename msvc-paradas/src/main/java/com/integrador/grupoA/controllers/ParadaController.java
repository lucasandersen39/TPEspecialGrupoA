package com.integrador.grupoA.controllers;

import com.integrador.grupoA.dto.ParadaMonopatinResponseDTO;
import com.integrador.grupoA.repositories.ParadaRepository;
import com.integrador.grupoA.services.ParadaService;
import com.integrador.grupoA.dto.ParadaRequestDTO;
import com.integrador.grupoA.dto.ParadaResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/parada")
public class ParadaController {

    @Autowired
    private ParadaService paradaService;

    @Autowired
    private ParadaRepository paradaRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public List<ParadaResponseDTO> listar(){return paradaService.listar();}

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Optional<ParadaResponseDTO> buscarPorId(@PathVariable Long id){return paradaService.buscarPorId(id);}

    @GetMapping("/coordenadas")
    public Optional<ParadaResponseDTO> buscarPorCoordenada(@RequestParam  Double x, @RequestParam  Double y){return paradaService.buscarPorCoordenada(x, y);}

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("")
    public ResponseEntity<Optional<ParadaResponseDTO>> crearParada(@RequestBody @Valid ParadaRequestDTO parada){
        final Optional<ParadaResponseDTO> result = paradaService.crearParada(parada);
        return ResponseEntity.accepted().body(result);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Optional<ParadaResponseDTO>> modificarParada(@RequestBody @Valid ParadaRequestDTO parada, @PathVariable Long id){
        final Optional<ParadaResponseDTO> result = paradaService.modificarParada(parada, id);
        return ResponseEntity.accepted().body(result);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarParada(@PathVariable Long id){paradaService.eliminarParada(id);}

    @GetMapping("/monopatinesCercanos/x/{x}/y/{y}")
    public ParadaMonopatinResponseDTO monopatinesCercanos(@PathVariable  Double x, @PathVariable  Double y){
        return paradaService.buscarMonopatinesCercanos(x, y);
    }

    @GetMapping("/id_valido/{id}")
    public ResponseEntity<Void> validarParada(@PathVariable Long id) {
        boolean existe = paradaRepository.existsById(id);
        return existe ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
