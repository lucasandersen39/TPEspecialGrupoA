package com.integrador.grupoA.DTO;

public class DtoTarifa {

    private long id;
    private String tipo_tarifa;
    private double monto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo_tarifa() {
        return tipo_tarifa;
    }

    public void setTipo_tarifa(String tipo_tarifa) {
        this.tipo_tarifa = tipo_tarifa;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
