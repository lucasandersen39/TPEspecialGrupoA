package com.integrador.grupoA.Web;

import com.integrador.grupoA.DTO.*;
import com.integrador.grupoA.Domain.Viaje;
import com.integrador.grupoA.Service.ViajeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/viaje")
public class ViajeController {
    @Autowired
    private ViajeService viajeService;

    @GetMapping
    public List<DtoViajeResponse> listarViajes() {
        return viajeService.listarViajes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoViajeResponse> getViaje(@PathVariable int id) {
        try {
            DtoViajeResponse viaje = viajeService.obtenerViajePorId(id);
            return ResponseEntity.ok(viaje);
        } catch (RuntimeException e) {
            // Devuelve un código HTTP 404 si el viaje no existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("")
    public ResponseEntity<DtoViajeResponse> createViaje(@Valid @RequestBody DtoViajeRequest viaje) {
        System.out.println("Viaje a crear: " + viaje);
        DtoViajeResponse viajeResponse = viajeService.crearViaje(viaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(viajeResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteViaje(@PathVariable int id) {
        try {
            viajeService.deleteViaje(id);
            return ResponseEntity.noContent().build(); // Devuelve código HTTP 204 No Content
        } catch (RuntimeException e) {
            // Devuelve un error HTTP 404 si no se encuentra el ID
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtoViajeResponse> updateViaje(
            @PathVariable int id,
            @Valid @RequestBody DtoViajeRequest dto) {
        try {
            DtoViajeResponse actualizado = viajeService.updateViaje(id, dto);
            return ResponseEntity.ok(actualizado); // Retorna el nuevo estado del viaje
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 si no se encuentra el recurso
        }
    }



    @PostMapping("/facturacion")
    public ResponseEntity<Double> obtenerFacturacionEntreFechas(@RequestBody DtoFacturacionRequest request) {
        try {
            // Obtén los parámetros del JSON recibido
            LocalDateTime fechaInicio = request.getFechaInicio();
            LocalDateTime fechaFin = request.getFechaFin();

            // Llama al servicio con las fechas proporcionadas
            double totalFacturacion = viajeService.calcularFacturacionEntreFechas(fechaInicio, fechaFin);
            return ResponseEntity.ok(totalFacturacion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/pausas")
    public ResponseEntity<Map<String, Double>> obtenerTiemposPausadosPorMonopatin() {
        Map<String, Double> reporte = viajeService.obtenerTiempoPausadoTotal();
        return ResponseEntity.ok(reporte);
    }

//    @GetMapping("/monopatines/mas-viajes")
//    public ResponseEntity<List<DtoResponseMonopatinesMasViajes>> obtenerMonopatinesConMasDeXViajes(
//            @RequestParam int anio,
//            @RequestParam long minViajes) {
//
//        List<DtoResponseMonopatinesMasViajes> respuesta = viajeService.obtenerDetallesMonopatinesConMasViajes(anio, minViajes);
//        return ResponseEntity.ok(respuesta);
//    }

    @PostMapping("/monopatines/mas-viajes")
    public ResponseEntity<List<DtoResponseMonopatin>> obtenerMonopatinesConMasDeXViajes(
            @RequestBody DtoMonopatinesRequest request) {

        // Extraer los valores del DTO
        int anio = request.getAnio();
        long minViajes = request.getMinViajes();

        // Usar el servicio con los datos extraídos
        List<DtoResponseMonopatin> respuesta = viajeService.obtenerDetallesMonopatinesConMasViajes(anio, minViajes);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/usuarios/mas-viajes")
    public ResponseEntity<List<DtoResponseUsuarioConViajes>> obtenerUsuariosConMasViajes(
            @RequestParam LocalDateTime fechaInicio,
            @RequestParam LocalDateTime fechaFin,
            @RequestParam(required = false) String tipoUsuario,
            @RequestBody List<DtoUsuarioResponse> usuarios) {

        List<DtoResponseUsuarioConViajes> respuesta = viajeService.obtenerUsuariosConMasViajes(
                fechaInicio, fechaFin, usuarios, tipoUsuario);

        return ResponseEntity.ok(respuesta);
    }


}