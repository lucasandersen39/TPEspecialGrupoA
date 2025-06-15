package com.integrador.grupoA.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tarifa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true)
    @NotBlank(message = "El campo tipo_tarifa no puede estar vacío")
    private String tipo_tarifa;

    @Column (nullable = false)
    @Positive(message = "El monto debe ser positivo")
    private double monto;
}