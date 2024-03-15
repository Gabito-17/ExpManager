package com.example.pooproject.Model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Expediente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    String nroExpediente;
    @Basic
    String iniciante;
    @Basic
    String texto;
    @Temporal(TemporalType.DATE)
    Date fechaIngreso;
    @Basic
    Boolean estado;

    public Expediente() {
    }

    public Expediente(String nroExpediente, String iniciante, String texto, LocalDate fechaIngreso, Boolean estado) {
        this.nroExpediente = nroExpediente;
        this.iniciante = iniciante;
        this.texto = texto;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
    }


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
