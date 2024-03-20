package edu.unam.pooproject.modelo;

import java.time.LocalDate;

public class Orden {
    int nroOrden;
    int nroReunion;
    LocalDate fecha;

    public Orden() {
    }

    public Orden(int nroOrden, int nroReunion, LocalDate fecha) {
        this.nroOrden = nroOrden;
        this.nroReunion = nroReunion;
        this.fecha = fecha;
    }


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
