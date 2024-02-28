package com.example.pooproject.Model;

import java.util.Date;

public class Acciones {
    Date fecha;
    String cambio;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }
}
