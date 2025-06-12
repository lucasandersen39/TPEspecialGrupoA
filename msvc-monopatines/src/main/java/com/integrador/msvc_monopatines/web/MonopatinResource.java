package com.integrador.msvc_monopatines.web;

import com.integrador.msvc_monopatines.service.MonopatinService;
import com.integrador.msvc_monopatines.service.dto.request.MonopatinRequestDTO;
import com.integrador.msvc_monopatines.service.dto.response.MonopatinResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monopatin")
@RequiredArgsConstructor
public class MonopatinResource {
    private final MonopatinService monopatinService;

    @PostMapping
    public ResponseEntity<MonopatinResponseDTO> saveMonopatin(@RequestBody MonopatinRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(monopatinService.saveMonopatin(dto));
    }

    @GetMapping
    public List<MonopatinResponseDTO> getAllMonopatines() {
        return monopatinService.getAllMonopatines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonopatinResponseDTO> getMonopatinById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(monopatinService.getMonopatinById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonopatinResponseDTO> updateMonopatin(@PathVariable String id, @RequestBody MonopatinRequestDTO dto) {
        try {
            return ResponseEntity.ok(monopatinService.updateMonopatin(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMonopatin(@PathVariable String id) {
        boolean deleted = monopatinService.deleteMonopatin(id);
        return deleted
                ? ResponseEntity.ok(true)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }


}

