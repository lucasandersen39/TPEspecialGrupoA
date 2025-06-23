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
    @Column(nullable = false)
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


}
