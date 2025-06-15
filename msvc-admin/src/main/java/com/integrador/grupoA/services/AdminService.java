package com.integrador.grupoA.services;

import com.integrador.grupoA.entities.Tarifa;
import com.integrador.grupoA.exceptions.custom.BusinessValidationException;
import com.integrador.grupoA.exceptions.custom.DuplicateEntityException;
import com.integrador.grupoA.exceptions.custom.ResourceNotFoundException;
import com.integrador.grupoA.repository.TarifaRepository;
import com.integrador.grupoA.services.dto.tarifas.tarifaRequest.TarifaRequestDTO;
import com.integrador.grupoA.services.dto.tarifas.tarifaResponse.TarifaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    TarifaRepository tarifaRepository;

    //Obtiene una lista de todas las tarifas
    @Transactional (readOnly = true)
    public List<TarifaResponseDTO> obtenerTarifas(){
        List<TarifaResponseDTO> tarifas = tarifaRepository.findAllTarifas();
        if (tarifas.isEmpty())
            throw new ResourceNotFoundException("No existen tarifas");
        return tarifas;
    }

    // Obtiene una TarifaResponseDTO por su id
    @Transactional(readOnly = true)
    public TarifaResponseDTO findTarifaPorId(Long id){
        TarifaResponseDTO tarifa = tarifaRepository.findTarifaPorId(id);
        if (tarifa == null)
            throw new ResourceNotFoundException(String.format("No existen tarifas con el ID: %d", id));
        return tarifa;
    }

    // Obtiene una TarifaResponseDTO por su tipo
    @Transactional (readOnly = true)
    public TarifaResponseDTO obtenerTarifaPorTipo(String tipoTarifa){
        TarifaResponseDTO tarifa = tarifaRepository.findTarifaPorTipo(tipoTarifa);
        if (tarifa == null)
            throw new ResourceNotFoundException(String.format("No existen tarifas con el tipo: %s", tipoTarifa));
        return tarifa;
    }

    // Agrega una nueva tarifa
    @Transactional
    public TarifaResponseDTO agregarTarifa(TarifaRequestDTO tarifaRequestDTO){
        validarTipoTarifa(tarifaRequestDTO.getTipo_tarifa());
        validarMonto(tarifaRequestDTO.getMonto());
        Tarifa tarifa = convertirATarifa(tarifaRequestDTO);
        tarifaRepository.save(tarifa);
        return convertirADTO(tarifa);
    }

    // Elimina una tarifa por su id
    @Transactional
    public void borrarTarifa(Long id){
        findById(id);
        tarifaRepository.deleteById(id);
    }

    // Modifica una tarifa por su id
    @Transactional
    public TarifaResponseDTO modificarTarifa(Long id, TarifaRequestDTO tarifaRequestDTO){
        Tarifa tarifaEncontrada = findById(id);
        validarId(id, tarifaEncontrada);
        validarMonto(tarifaRequestDTO.getMonto());

        if (!tarifaEncontrada.getTipo_tarifa().equals(tarifaRequestDTO.getTipo_tarifa()))
            validarTipoTarifa(tarifaRequestDTO.getTipo_tarifa());

        tarifaEncontrada.setTipo_tarifa(tarifaRequestDTO.getTipo_tarifa());
        tarifaEncontrada.setMonto(tarifaRequestDTO.getMonto());
        tarifaRepository.save(tarifaEncontrada);
        return convertirADTO(tarifaEncontrada);
    }

    // Obtiene una tarifa por su id
    @Transactional(readOnly = true)
    public Tarifa findById(Long id){
        return tarifaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Tarifa no encontrada con ID: %d", id)));
    }

    // Ajusta los precios de las tarifas y los habilita a partir de cierta fecha.
    @Transactional
    public TarifaResponseDTO actualizarPrecios(Long id, double monto){
        Tarifa tarifa = findById(id);
        validarMonto(monto);
        tarifa.setMonto(monto);
        tarifaRepository.save(tarifa);
        return convertirADTO(tarifa);
    }

    /** ----- MÉTODOS AUXILIARES CON MANEJO DE EXCEPCIONES ---- */

    // Verifica que no exista una tarifa con el mismo nombre y que el tipo no sea nulo o vacío
    private void validarTipoTarifa(String tipoTarifa){
        if (tarifaRepository.existeTarifaPorTipo(tipoTarifa))
            throw new DuplicateEntityException(String.format("Ya existe una tarifa con el tipo: %s", tipoTarifa));

        if (tipoTarifa == null || tipoTarifa.trim().isEmpty())
            throw new BusinessValidationException("El tipo de tarifa es obligatorio");
    }

    // Verifica que el monto sea positivo y no nulo
    private void validarMonto(double monto){
        if (monto <= 0.0)
            throw new BusinessValidationException("El monto debe ser positivo");
    }

    // Verifica que no se intente cambiar el ID
    private void validarId(Long id, Tarifa tarifa){
        if (!id.equals(tarifa.getId()))
            throw new BusinessValidationException("El ID de la tarifa no puede ser modificado");
    }

    // Convierte una tarifa a TarifaResponseDTO.
    private TarifaResponseDTO convertirADTO(Tarifa tarifa){
        TarifaResponseDTO tarifaResponseDTO = new TarifaResponseDTO();
        tarifaResponseDTO.setTipo_tarifa(tarifa.getTipo_tarifa());
        tarifaResponseDTO.setMonto(tarifa.getMonto());
        return tarifaResponseDTO;
    }

    // Convierte una TarifaResponseDTO a una tarifa.
    private Tarifa convertirATarifa(TarifaRequestDTO tarifaRequestDTO){
        Tarifa tarifa = new Tarifa();
        tarifa.setTipo_tarifa(tarifaRequestDTO.getTipo_tarifa());
        tarifa.setMonto(tarifaRequestDTO.getMonto());
        return tarifa;
    }
}
