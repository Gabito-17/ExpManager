package com.example.pooproject.Model;

import java.time.LocalDate;

public class Reunion {
    int nroReunion;
    LocalDate fecha;
    int orden;

    public Reunion() {
    }

    public Reunion(int nroReunion, LocalDate fecha, int orden) {
        this.nroReunion = nroReunion;
        this.fecha = fecha;
        this.orden = orden;
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

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
}
