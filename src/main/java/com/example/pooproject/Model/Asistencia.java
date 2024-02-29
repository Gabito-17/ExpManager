package com.example.pooproject.Model;

import java.time.LocalDate;

public class Asistencia {
    String dniMiembro;
    LocalDate fecha;
    Boolean asiste;

    public String getDniMiembro() {
        return dniMiembro;
    }

    public void setDniMiembro(String dniMiembro) {
        this.dniMiembro = dniMiembro;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Boolean getAsiste() {
        return asiste;
    }

    public void setAsiste(Boolean asiste) {
        this.asiste = asiste;
    }

}
