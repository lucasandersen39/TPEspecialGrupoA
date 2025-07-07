package com.integrador.grupoA.controllers;

import com.integrador.grupoA.dto.ParadaMonopatinResponseDTO;
import com.integrador.grupoA.repositories.ParadaRepository;
import com.integrador.grupoA.services.ParadaService;
import com.integrador.grupoA.dto.ParadaRequestDTO;
import com.integrador.grupoA.dto.ParadaResponseDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/parada")
@Tag(name = "Paradas", description = "Operaciones relacionadas a paradas")
public class ParadaController {

    @Autowired
    private ParadaService paradaService;

    @Autowired
    private ParadaRepository paradaRepository;

    @Operation(summary = "Listar todas las paradas")
    @GetMapping("")
    public List<ParadaResponseDTO> listar(){return paradaService.listar();}

    @Operation(summary = "Buscar parada por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parada encontrada"),
            @ApiResponse(responseCode = "404", description = "Parada no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ParadaResponseDTO> buscarPorId(@PathVariable Long id){
        return paradaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar parada por coordenadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parada encontrada por coordenadas"),
            @ApiResponse(responseCode = "404", description = "No se encontró parada con esas coordenadas")
    })
    @GetMapping("/coordenadas")
    public ResponseEntity<ParadaResponseDTO> buscarPorCoordenada(@Parameter(description = "Coordenada X") @RequestParam  Double x,
                                                                 @Parameter(description = "Coordenada Y") @RequestParam  Double y){
        return paradaService.buscarPorCoordenada(x, y)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva parada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Parada creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "409", description = "Ya existe una parada en esas coordenadas")
    })
    @PostMapping("")
    public ResponseEntity<ParadaResponseDTO> crearParada(@Parameter(description = "Datos de la parada") @RequestBody @Valid ParadaRequestDTO parada){
        final Optional<ParadaResponseDTO> result = paradaService.crearParada(parada);
        return result
                .map(ResponseEntity.accepted()::body)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @Operation(summary = "Modificar una parada existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Parada modificada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Parada no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ParadaResponseDTO> modificarParada(
            @Parameter(description = "Datos actualizados de la parada") @RequestBody @Valid ParadaRequestDTO parada,
            @Parameter(description = "ID de la parada a modificar") @PathVariable Long id){
        final Optional<ParadaResponseDTO> result = paradaService.modificarParada(parada, id);
        return result
                .map(ResponseEntity.accepted()::body)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar una parada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Parada eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Parada no encontrada")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> eliminarParada(@Parameter(description = "ID de la parada a eliminar") @PathVariable Long id){
        paradaService.eliminarParada(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar monopatines cercanos a una coordenada")
    @GetMapping("/monopatinesCercanos/x/{x}/y/{y}")
    public ParadaMonopatinResponseDTO monopatinesCercanos(@Parameter(description = "Coordenada X") @PathVariable  Double x,
                                                          @Parameter(description = "Coordenada Y") @PathVariable  Double y){
        return paradaService.buscarMonopatinesCercanos(x, y);
    }

    @Operation(summary = "Validar si existe una parada por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parada válida"),
            @ApiResponse(responseCode = "404", description = "Parada no válida")
    })
    @GetMapping("/id_valido/{id}")
    public ResponseEntity<Void> validarParada(@Parameter(description = "ID de la parada a validar") @PathVariable Long id) {
        boolean existe = paradaRepository.existsById(id);
        return existe ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
