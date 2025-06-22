package com.integrador.grupoA.DTO;

import java.time.LocalDateTime;

public class ViajeResponseDTO {


        private int id;

        private int idUsuario;

        private String idMonopatin;

        private LocalDateTime fechaInicio;

        private LocalDateTime fechaFin;

        private double kmRecorridos;

        private double costoTotal;

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public int getIdUsuario() {
                return idUsuario;
        }

        public void setIdUsuario(int idUsuario) {
                this.idUsuario = idUsuario;
        }

        public String getIdMonopatin() {
                return idMonopatin;
        }

        public void setIdMonopatin(String idMonopatin) {
                this.idMonopatin = idMonopatin;
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
}

