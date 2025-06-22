package com.integrador.grupoA.DTO;

public class TiempoPausadoDTO {

    private String idMonopatin;
    private Double tiempoTotalPausado;

    public TiempoPausadoDTO(String idMonopatin, Double tiempoTotalPausado) {
        this.idMonopatin = idMonopatin;
        this.tiempoTotalPausado = tiempoTotalPausado;
    }

    public String getIdMonopatin() {
        return idMonopatin;
    }

    public void setIdMonopatin(String idMonopatin) {
        this.idMonopatin = idMonopatin;
    }

    public Double getTiempoTotalPausado() {
        return tiempoTotalPausado;
    }

    public void setTiempoTotalPausado(Double tiempoTotalPausado) {
        this.tiempoTotalPausado = tiempoTotalPausado;
    }
}
