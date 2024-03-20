package edu.unam.pooproject.modelo;

import java.time.LocalDate;

public class Minuta {
    int nroMinuta;
    LocalDate fecha;
    String lugar;
    String resumen;
    String decision;

    public Minuta() {
    }

    public Minuta(int nroMinuta, LocalDate fecha, String lugar, String resumen, String decision) {
        this.nroMinuta = nroMinuta;
        this.fecha = fecha;
        this.lugar = lugar;
        this.resumen = resumen;
        this.decision = decision;
    }


    public int getNroMinuta() {
        return nroMinuta;
    }

    public void setNroMinuta(int nroMinuta) {
        this.nroMinuta = nroMinuta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

}
