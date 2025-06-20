package com.integrador.grupoA.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @Column(name="id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefono;

    @Column(name = "nombre_usuario", nullable = false, unique = true)
    private String nombreUsuario;
    //Cuando creas un usuario, tenes que registrar una cuenta?

    @Column(name="tipo_usuario", nullable = false)
    private String tipoUsuario; //Basico o Premium

    @Column(nullable = true)
    private Double dinero;

    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;

    @Column(nullable = false)
    private boolean activo;

}
