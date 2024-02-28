package com.example.pooproject.Model;

import java.time.LocalDate;

public class Expediente {
    String nroExpediente;
    String iniciante;
    String texto;
    LocalDate fechaIngreso;
    Boolean estado;

    public String getNroExpediente() {
        return nroExpediente;
    }

    public void setNroExpediente(String nroExpediente) {
        this.nroExpediente = nroExpediente;
    }

    public String getIniciante() {
        return iniciante;
    }

    public void setIniciante(String iniciante) {
        this.iniciante = iniciante;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
