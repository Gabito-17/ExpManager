package com.example.pooproject.Model;

import java.time.LocalDate;

public class Orden {
    int nroOrden;
    int nroReunion;
    LocalDate fecha;

    public int getNroOrden() {
        return nroOrden;
    }

    public void setNroOrden(int nroOrden) {
        this.nroOrden = nroOrden;
    }

    public int getNroReunion() {
        return nroReunion;
    }

    public void setNroReunion(int nroReunion) {
        this.nroReunion = nroReunion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
