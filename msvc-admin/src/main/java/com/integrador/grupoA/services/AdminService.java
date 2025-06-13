package com.integrador.grupoA.services;

import com.integrador.grupoA.entities.Tarifa;
import com.integrador.grupoA.exceptions.custom.BusinessValidationException;
import com.integrador.grupoA.exceptions.custom.DuplicateEntityException;
import com.integrador.grupoA.exceptions.custom.ResourceNotFoundException;
import com.integrador.grupoA.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

    @Autowired
    TarifaRepository tarifaRepository;

    // Agrega una nueva tarifa
    @Transactional
    public Tarifa agregarTarifa(Tarifa tarifa){
        validarTipoTarifa(tarifa.getTipo_tarifa());
        validarMonto(tarifa.getMonto());
        return tarifaRepository.save(tarifa);
    }

    // Elimina una tarifa por su id
    @Transactional
    public void borrarTarifa(Long id){
        findById(id);
        tarifaRepository.deleteById(id);
    }

    // Modifica una tarifa por su id
    @Transactional
    public Tarifa modificarTarifa(Long id, Tarifa tarifa){
        Tarifa tarifaEncontrada = findById(id);
        validarId(id, tarifa);
        validarMonto(tarifa.getMonto());

        if (!tarifaEncontrada.getTipo_tarifa().equals( tarifa.getTipo_tarifa() ))
            validarTipoTarifa(tarifa.getTipo_tarifa());

        tarifaEncontrada.setTipo_tarifa(tarifa.getTipo_tarifa());
        tarifaEncontrada.setMonto(tarifa.getMonto());

        return tarifaRepository.save(tarifaEncontrada);
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
    public Tarifa actualizarPrecios(Long id, double monto){
        Tarifa tarifa = findById(id);
        validarMonto(monto);
        tarifa.setMonto(monto);

        return tarifaRepository.save(tarifa);
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

}
