package com.integrador.grupoA.Service;

import com.integrador.grupoA.Client.MonopatinClient;
import com.integrador.grupoA.Client.TarifaFeignClient;
import com.integrador.grupoA.Client.UsuarioFeignClient;
import com.integrador.grupoA.DTO.*;
import com.integrador.grupoA.DTO.DtoTarifa;
import com.integrador.grupoA.Domain.Viaje;
import com.integrador.grupoA.Repositories.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ViajeService {
    @Autowired
    private ViajeRepository viajeRepository;
    @Autowired
    private TarifaFeignClient tarifaFeignClient;
    @Autowired
    private UsuarioFeignClient usuarioFeignClient;
    @Autowired
    private MonopatinClient monopatinClient;

    public List<ViajeResponseDTO> listarViajes() {
        // Consulta todos los viajes desde la base de datos, los convierte a ViajeResponseDTO y los retorna
        return viajeRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }


    public ViajeResponseDTO obtenerViajePorId(int id) {
        // Busca el viaje por ID, lanza una excepción si no se encuentra
        Viaje viaje = viajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado con ID: " + id));
        // Convierte y retorna el viaje como un ViajeResponseDTO
        return convertToResponseDTO(viaje);
    }

//    public ViajeResponseDTO createViaje(ViajeDTO viajeCreateDTO) {
//        // Convierte el DTO a una entidad, la guarda en la base de datos y retorna el resultado como ResponseDTO
//        Viaje nuevoViaje = convertToEntity(viajeCreateDTO);
//        Viaje viajeGuardado = viajeRepository.save(nuevoViaje);
//        return convertToResponseDTO(viajeGuardado);
//    }

    public ViajeResponseDTO createViaje(ViajeDTO viajeCreateDTO) {
        // Verificar que el monopatín existe en el microservicio de monopatines
        boolean exists = monopatinClient.existeMonopatin(viajeCreateDTO.getIdMonopatin());
        if (!exists) {
            throw new IllegalArgumentException("El ID del monopatín no existe: " + viajeCreateDTO.getIdMonopatin());
        }

        // Convierte el DTO a una entidad, la guarda en la base de datos y retorna el resultado como ResponseDTO
        Viaje nuevoViaje = convertToEntity(viajeCreateDTO);
        Viaje viajeGuardado = viajeRepository.save(nuevoViaje);
        return convertToResponseDTO(viajeGuardado);
    }


    public void deleteViaje(int id) {
        // Valida si el viaje existe antes de eliminarlo
        if (!viajeRepository.existsById(id)) {
            throw new RuntimeException("Viaje no encontrado con ID: " + id);
        }
        // Elimina el viaje de la base de datos
        viajeRepository.deleteById(id);
    }
    public ViajeResponseDTO updateViaje(int id, ViajeDTO viajeUpdateDTO) {
        // Valida si el viaje existe
        Viaje viajeExistente = viajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado con ID: " + id));

        // Actualiza los valores del viaje con los datos del DTO
        viajeExistente.setIdUsuario(viajeUpdateDTO.getIdUsuario());
        viajeExistente.setIdMonopatin(viajeUpdateDTO.getIdMonopatin());
        viajeExistente.setFechaInicio(viajeUpdateDTO.getFechaInicio());
        viajeExistente.setFechaFin(viajeUpdateDTO.getFechaFin());
        viajeExistente.setKmRecorridos(viajeUpdateDTO.getKmRecorridos());
        viajeExistente.setCostoTotal(viajeUpdateDTO.getCostoTotal());

        // Guarda los cambios en la base de datos
        Viaje viajeActualizado = viajeRepository.save(viajeExistente);

        // Retorna el viaje actualizado como un DTO de respuesta
        return convertToResponseDTO(viajeActualizado);
    }

    // Convierte una entidad Viaje a un DTO de respuesta
    private ViajeResponseDTO convertToResponseDTO(Viaje viaje) {
        ViajeResponseDTO dto = new ViajeResponseDTO();
        dto.setId(viaje.getId());
        dto.setIdUsuario(viaje.getIdUsuario());
        dto.setIdMonopatin(viaje.getIdMonopatin());
        dto.setFechaInicio(viaje.getFechaInicio());
        dto.setFechaFin(viaje.getFechaFin());
        dto.setKmRecorridos(viaje.getKmRecorridos());
        dto.setCostoTotal(viaje.getCostoTotal());
        return dto;
    }

    // Convierte un DTO a una entidad Viaje
    private Viaje convertToEntity(ViajeDTO dto) {
        Viaje viaje = new Viaje();
        viaje.setIdUsuario(dto.getIdUsuario());
        viaje.setIdMonopatin(dto.getIdMonopatin());
        viaje.setFechaInicio(dto.getFechaInicio());
        viaje.setFechaFin(dto.getFechaFin());
        viaje.setKmRecorridos(dto.getKmRecorridos());
        viaje.setCostoTotal(dto.getCostoTotal());
        return viaje;
    }


    public Viaje calcularCostoViaje(Viaje viaje) {
        // Consultar el usuario por su id
        DtoUsuario usuario = usuarioFeignClient.obtenerUsuarioPorId(viaje.getIdUsuario());

        // Determinar la tarifa según el tipo de usuario (Premium o Básico)
        String tipoUsuario = usuario != null ? usuario.getTipoUsuario() : "Basico";
        DtoTarifa tarifa = tarifaFeignClient.obtenerTarifaPorTipo(tipoUsuario);

        // Calcular costo total del viaje
        if (tarifa != null) {
            double costo = tarifa.getMonto() * viaje.getKmRecorridos();
            viaje.setCostoTotal(costo);
        } else {
            throw new RuntimeException("No se encontró tarifa para el tipo de usuario: " + tipoUsuario);
        }

        return viaje; // Devuelve el viaje con el costo calculado
    }


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

    public Map<String, Double> obtenerTiempoPausadoTotal() {
        List<TiempoPausadoDTO> lista = viajeRepository.obtenerTiemposPausados();

        return lista.stream()
                .collect(Collectors.toMap(
                        TiempoPausadoDTO::getIdMonopatin,
                        dto -> Optional.ofNullable(dto.getTiempoTotalPausado()).orElse(0.0)
                ));
    }

    public List<DtoResponseMonopatinesMasViajes> obtenerDetallesMonopatinesConMasViajes(int anio, long minViajes) {
        // Obtener los monopatines y sus IDs que superan los "X" viajes
        List<DtoResponseMonopatinesMasViajes> resultados = viajeRepository.findMonopatinesConMasDeXViajesEnAnio(anio, minViajes);

        // Extraer los IDs de los monopatines
        List<String> idsMonopatines = resultados.stream()
                .map(DtoResponseMonopatinesMasViajes::getIdMonopatin) // Accede al atributo "idMonopatin"
                .collect(Collectors.toList());

        // Llamada al microservicio de monopatines para obtener detalles
        List<DtoResponseMonopatinesMasViajes> detallesMonopatines = monopatinClient.obtenerDetallesMonopatines(idsMonopatines);

        return detallesMonopatines;
    }

    public List<DtoResponseUsuarioConViajes> obtenerUsuariosConMasViajes(
            LocalDateTime fechaInicio, LocalDateTime fechaFin,
            List<com.integrador.grupoA.DTO.DtoUsuario> usuarios, String tipoUsuario) {

        // Obtener los usuarios con más viajes en el período
        List<Object[]> viajesPorUsuario = viajeRepository.findUsuariosConMasViajes(fechaInicio, fechaFin);

        // Mapear los resultados del repositorio
        Map<Integer, Long> mapaViajes = viajesPorUsuario.stream()
                .collect(Collectors.toMap(
                        resultado -> (Integer) resultado[0],      // idUsuario
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



    public List<DtoUsoMonopatin> obtenerEstadisticasDeUso(LocalDateTime fechaInicio, LocalDateTime fechaFin, List<Integer> idsUsuarios) {
        // Obtener las estadísticas desde el repositorio
        List<Object[]> resultados = viajeRepository.obtenerEstadisticasDeUsuarios(fechaInicio, fechaFin, idsUsuarios);

        // Convertir resultados en una lista de DtoUsoMonopatin
        return resultados.stream()
                .map(resultado -> new DtoUsoMonopatin(
                        (Integer) resultado[0],   // idUsuario
                        (Long) resultado[1],     // totalViajes
                        (Double) resultado[2],   // totalKm
                        (Double) resultado[3]))  // totalTiempo
                .collect(Collectors.toList());
    }




}
