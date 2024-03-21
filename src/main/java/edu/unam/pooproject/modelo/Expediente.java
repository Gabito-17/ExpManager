package edu.unam.pooproject.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "expedientes", schema = "public")
public class Expediente {

    @Id
    @Column(name = "nroExpediente")
    int idExpediente;
    @Basic
    @Column(name = "iniciante")
    String iniciante;
    @Column(name = "texto")
    String texto;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_ingreso")
    Date fechaIngreso;
    @Column(name = "estado")
    Boolean estado;


    public Expediente() {
    }

    public Expediente(int nroExpediente, String iniciante, String texto, Date fechaIngreso, Boolean estado) {
        this.idExpediente = nroExpediente;
        this.iniciante = iniciante;
        this.texto = texto;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
    }


    public int getNroExpediente() {
        return idExpediente;
    }

    public void setNroExpediente(int nroExpediente) {
        this.idExpediente = nroExpediente;
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

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
