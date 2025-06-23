package com.integrador.msvc_monopatines.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "monopatines")
public class Monopatin {
    @Id
    private String idMonopatin;
    private int estado;
    private Long idParada;
    private double kmRecorridos;
    private double tiempoUsado;
}
