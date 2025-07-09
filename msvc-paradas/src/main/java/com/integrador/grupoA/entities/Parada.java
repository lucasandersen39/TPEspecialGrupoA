package com.integrador.grupoA.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"x", "y"})
        }
)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Parada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column
    private String nombre;

    @Setter
    @Column(nullable = false)
    private Double x;

    @Setter
    @Column(nullable = false)
    private Double y;

    public Parada(final String nombre, final Double x, final Double y) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
    }
}
