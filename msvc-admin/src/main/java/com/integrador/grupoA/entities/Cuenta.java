package com.integrador.grupoA.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cuenta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El campo entidad_bancaria no puede estar vacío")
    private String entidad_bancaria;

    @Column (nullable = false, unique = true)
    @NotNull(message = "El campo numero_cuenta no puede estar vacío")
    private int numero_cuenta;

    @Column
    @PositiveOrZero(message = "El saldo debe ser cero o positivo")
    private double saldo;

    @Column(nullable = false, unique = true)
    @NotNull(message = "El campo id_titular no puede estar vacío")
    private Long id_titular;

    public Cuenta(String entidad_bancaria, int numero_cuenta, double saldo, Long id_titular) {
        this.entidad_bancaria = entidad_bancaria;
        this.numero_cuenta = numero_cuenta;
        this.saldo = saldo;
        this.id_titular = id_titular;
    }
}