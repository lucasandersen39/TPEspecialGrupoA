package com.integrador.grupoA.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Table(name = "viaje")
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int idUsuario;
    @Column
    private String idMonopatin;
    @Column
    private LocalDateTime fechaInicio;
    @Column
    private LocalDateTime fechaFin;
    @Column
    private double kmRecorridos;
    @Column
    private double costoTotal;
    @Column
    private double tiempoPausado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdMonopatin() {
        return idMonopatin;
    }

    public void setIdMonopatin(String idMonopatin) {
        this.idMonopatin = idMonopatin;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getKmRecorridos() {
        return kmRecorridos;
    }

    public void setKmRecorridos(double kmRecorridos) {
        this.kmRecorridos = kmRecorridos;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public double getTiempoPausado() {
        return tiempoPausado;
    }

    public void setTiempoPausado(double tiempoPausado) {
        this.tiempoPausado = tiempoPausado;
    }
}
