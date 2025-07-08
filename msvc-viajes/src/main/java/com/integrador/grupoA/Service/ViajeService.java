package com.integrador.grupoA.Service;

import com.integrador.grupoA.Client.MonopatinClient;
import com.integrador.grupoA.Client.AdminFeignClient;
import com.integrador.grupoA.Client.UsuarioFeignClient;
import com.integrador.grupoA.DTO.*;
import com.integrador.grupoA.DTO.DtoTarifaResponse;
import com.integrador.grupoA.Domain.Viaje;
import com.integrador.grupoA.ExceptionHandler.ExceptionHandlerController;
import com.integrador.grupoA.Repositories.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ViajeService {
    @Autowired
    private ViajeRepository viajeRepository;
    @Autowired
    private AdminFeignClient tarifaFeignClient;
    @Autowired
    private UsuarioFeignClient usuarioFeignClient;
    @Autowired
    private MonopatinClient monopatinClient;

    @Transactional(readOnly = true)
    public List<DtoViajeResponse> listarViajes() {
        // Consulta todos los viajes desde la base de datos, los convierte a ViajeResponseDTO y los retorna
        return viajeRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DtoViajeResponse obtenerViajePorId(int id) {
        try {
            Viaje viaje = viajeRepository.findById(id)
                    .orElseThrow(() -> new ExceptionHandlerController.ViajeNotFoundException("El viaje con ID " + id + " no fue encontrado."));
            return convertToResponseDTO(viaje);
        } catch (ExceptionHandlerController.ViajeNotFoundException e) {
            throw e; // Propaga la excepción para que la manejen las capas superiores.
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al obtener el viaje con ID " + id, e);
        }
    }
    @Transactional
    public DtoViajeResponse crearViaje(DtoViajeRequest viajeRequest) {
        LocalDateTime fechaInicio;
        LocalDateTime fechaFin;
        if (viajeRequest.getFechaInicio() == null || viajeRequest.getFechaFin() == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin son obligatorias.");
        }

        fechaInicio = parseFecha(viajeRequest.getFechaInicio());
        fechaFin = parseFecha(viajeRequest.getFechaFin());
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }

        Viaje viaje= convertToEntity(viajeRequest,fechaInicio,fechaFin);

        viajeRepository.save(viaje);
        monopatinClient.sumarKmMonopatin(viaje.getIdMonopatin(), viaje.getKmRecorridos());
        monopatinClient.sumarTiempoUsoMonopatin(viaje.getIdMonopatin(), calcularDuracionEnHoras(viaje.getFechaInicio(), viaje.getFechaFin()));

        return convertToResponseDTO(viaje);
    }

    @Transactional
    public void deleteViaje(int id) {
        try {
            if (!viajeRepository.existsById(id)) {
                throw new ExceptionHandlerController.ViajeNotFoundException("No se puede eliminar. El viaje con ID " + id + " no fue encontrado.");
            }
            viajeRepository.deleteById(id);
        } catch (ExceptionHandlerController.ViajeNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ExceptionHandlerController.ViajeDeletionException("Error inesperado al eliminar el viaje con ID " + id);
        }
    }


    @Transactional
    public DtoViajeResponse updateViaje(int id, DtoViajeRequest viajeUpdateDTO) {
        LocalDateTime fechaInicio;
        LocalDateTime fechaFin;
        if (viajeUpdateDTO.getFechaInicio() == null || viajeUpdateDTO.getFechaFin() == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin son obligatorias.");
        }

        fechaInicio = parseFecha(viajeUpdateDTO.getFechaInicio());
        fechaFin = parseFecha(viajeUpdateDTO.getFechaFin());
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        // Valida si el viaje existe
        Viaje viajeExistente = viajeRepository.findById(id)
                .orElseThrow(() -> new ExceptionHandlerController.ViajeUpdateException("Viaje no encontrado con ID: " + id));

        // Actualiza los valores del viaje con los datos del DTO
        viajeExistente.setIdUsuario(viajeUpdateDTO.getIdUsuario());
        viajeExistente.setIdMonopatin(viajeUpdateDTO.getIdMonopatin());
        viajeExistente.setFechaInicio(fechaInicio);
        viajeExistente.setFechaFin(fechaFin);
        viajeExistente.setKmRecorridos(viajeUpdateDTO.getKmRecorridos());
        viajeExistente.setCostoTotal(calcularCostoViaje(viajeUpdateDTO));
        viajeExistente.setTiempoPausado(viajeUpdateDTO.getTiempoPausado());

        // Guarda los cambios en la base de datos
        Viaje viajeActualizado = viajeRepository.save(viajeExistente);

        // Retorna el viaje actualizado como un DTO de respuesta
        return convertToResponseDTO(viajeActualizado);
    }

    private LocalDateTime parseFecha(String fecha) {
        LocalDateTime fechaL = null;
        try {
            fechaL = LocalDateTime.parse(fecha);

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("El formato de fecha es inválido. Usá 'yyyy-MM-ddTHH:mm:ss', por ejemplo: '2025-06-23T14:50:00'");
        }
        return fechaL;

    }

    // Convierte una entidad Viaje a un DTO de respuesta
    private DtoViajeResponse convertToResponseDTO(Viaje viaje) {
        DtoViajeResponse dto = new DtoViajeResponse();
        dto.setId(viaje.getId());
        dto.setIdUsuario(viaje.getIdUsuario());
        dto.setIdMonopatin(viaje.getIdMonopatin());
        dto.setFechaInicio(viaje.getFechaInicio());
        dto.setFechaFin(viaje.getFechaFin());
        dto.setKmRecorridos(viaje.getKmRecorridos());
        dto.setCostoTotal(viaje.getCostoTotal());
        dto.setTiempoPausado(viaje.getTiempoPausado());
        return dto;
    }

    @Transactional(readOnly = true)
    // Convierte un DTO a una entidad Viaje
    public Viaje convertToEntity(DtoViajeRequest dto, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        Viaje viaje = new Viaje();
        viaje.setIdUsuario(dto.getIdUsuario());
        viaje.setIdMonopatin(dto.getIdMonopatin());
        viaje.setFechaInicio(fechaInicio);
        viaje.setFechaFin(fechaFin);
        viaje.setKmRecorridos(dto.getKmRecorridos());
        viaje.setCostoTotal(calcularCostoViaje(dto));
        viaje.setTiempoPausado(dto.getTiempoPausado());
        return viaje;
    }


@Transactional(readOnly = true)
public double calcularCostoViaje(DtoViajeRequest viajeRequest) {
    // Consultar el usuario por su ID
    DtoUsuarioResponse usuario = usuarioFeignClient.obtenerUsuarioPorId(viajeRequest.getIdUsuario());

    // Determinar la tarifa según el tipo de usuario (Premium o Básico)
    String tipoUsuario = usuario != null ? usuario.getTipoUsuario() : "Basico";

    // Obtener la tarifa según el tipo de usuario
    DtoTarifaResponse tarifa = tarifaFeignClient.obtenerTarifaPorTipo(tipoUsuario);
    double costo = 0.0;

    // Verificar que se haya encontrado la tarifa
    if (tarifa != null) {
        // Calcular el costo base del viaje
        costo = tarifa.getMonto() * viajeRequest.getKmRecorridos();

        // Verificar si el tiempo pausado excede los 15 minutos
        if (viajeRequest.getTiempoPausado() > 15) {
            // Aplicar recargo del 10% por tiempo pausado mayor a 15 minutos
            costo += costo * 0.10;
        }
    } else {
        throw new RuntimeException("No se encontró tarifa para el tipo de usuario: " + tipoUsuario);
    }

    return costo; // Devuelve el costo calculado del viaje
}

    @Transactional(readOnly = true)
    public double calcularFacturacionEntreFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        try {
            // Consulta los viajes entre las fechas dadas
            List<Viaje> viajes = viajeRepository.findViajesEntreFechas(fechaInicio, fechaFin);

            // Suma el costo total de todos los viajes
            return viajes.stream()
                    .mapToDouble(Viaje::getCostoTotal)
                    .sum();
        } catch (Exception e) {
            throw new RuntimeException("Error al calcular la facturación: " + e.getMessage(), e);
        }
    }
    @Transactional(readOnly = true)
    public Map<String, Double> obtenerTiempoPausadoTotal() {
        List<DtoTiempoPausado> lista = viajeRepository.obtenerTiemposPausados();

        return lista.stream()
                .collect(Collectors.toMap(
                        DtoTiempoPausado::getIdMonopatin,
                        dto -> Optional.ofNullable(dto.getTiempoTotalPausado()).orElse(0.0)
                ));
    }

    @Transactional(readOnly = true)
    public List<DtoMonopatinResponse> obtenerDetallesMonopatinesConMasViajes(int anio, long minViajes) {
        // Obtener los monopatines y sus IDs que superan los "X" viajes
        List<Object[]> resultados = viajeRepository.findMonopatinesConMasDeXViajesEnAnio(anio, minViajes);

        // Extraer los IDs de los monopatines
        List<String> idsMonopatines = resultados.stream()
                .map(obj -> (String) obj[0]) // Suponiendo que el ID está en la primera posición
                .collect(Collectors.toList());

        // Llamada al microservicio de monopatines para obtener detalles
        List<DtoMonopatinResponse> detallesMonopatin= new ArrayList<DtoMonopatinResponse>();
        for (String idMonopatin : idsMonopatines) {
            DtoMonopatinResponse monopatin = monopatinClient.obtenerMonopatinPorId(idMonopatin);
            detallesMonopatin.add(monopatin);
        }

        return detallesMonopatin;
    }

    @Transactional(readOnly = true)
    public List<DtoResponseUsuarioConViajes> obtenerUsuariosConMasViajes(
            LocalDateTime fechaInicio, LocalDateTime fechaFin,
            List<DtoUsuarioResponse> usuarios, String tipoUsuario) {

        // Obtener los usuarios con más viajes en el período
        List<Object[]> viajesPorUsuario = viajeRepository.findUsuariosConMasViajes(fechaInicio, fechaFin);

        // Mapear los resultados del repositorio
        Map<Long, Long> mapaViajes = viajesPorUsuario.stream()
                .collect(Collectors.toMap(
                        resultado -> (Long) resultado[0],      // idUsuario
                        resultado -> (Long) resultado[1]));      // totalViajes

        // Enlazar con los usuarios por tipo recibido desde el MSVC Usuarios y filtrar
        return usuarios.stream()
                .filter(usuario -> tipoUsuario == null || usuario.getTipoUsuario().equalsIgnoreCase(tipoUsuario)) // Filtrar por tipo
                .filter(usuario -> mapaViajes.containsKey(usuario.getIdUsuario())) // Solo usuarios con viajes
                .map(usuario -> new DtoResponseUsuarioConViajes(
                        usuario.getIdUsuario(),
                        mapaViajes.get(usuario.getIdUsuario()), // Obtener la cantidad de viajes del usuario
                        usuario.getTipoUsuario()))
                .sorted((u1, u2) -> Long.compare(u2.getTotalViajes(), u1.getTotalViajes())) // Ordenar de mayor a menor
                .collect(Collectors.toList());
    }


    public double obtenerTiempoUso(String usuarioId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        // Obtiene la lista de viajes del usuario entre las fechas indicadas
        List<Viaje> viajes = viajeRepository.findByUsuarioIdAndFechaInicioBetween(usuarioId, fechaInicio, fechaFin);

        // Calcula el tiempo total de uso
        return viajes.stream()
                .mapToDouble(viaje -> calcularDuracionEnHoras(viaje.getFechaInicio(), viaje.getFechaFin()))
                .sum();
    }

    // Calcula la duración en horas de un viaje usando fechaInicio y fechaFin
    private double calcularDuracionEnHoras(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        if (fechaInicio != null && fechaFin != null) {
            // Calcula la duración entre las dos fechas
            Duration duracion = Duration.between(fechaInicio, fechaFin);
            // Convierte la duración a horas (con decimales)
            return duracion.toMinutes() / 60.0;
        }
        return 0.0;
    }






}
