package edu.unam.pooproject.modelo;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "minuta", schema = "public")
public class Minuta {

    @Id
    @Column(name = "idMinuta")
    int idMinuta;
    @Basic
    @Column(name = "fecha")
    Date fecha;
    @Column(name = "lugar")
    String lugar;
    @Column(name = "resumen")
    String resumen;
    @Column(name = "decision")
    String decision;

    public Minuta() {
    }

    public Minuta(int idMinuta, Date fecha, String lugar, String resumen, String decision) {
        this.idMinuta = idMinuta;
        this.fecha = fecha;
        this.lugar = lugar;
        this.resumen = resumen;
        this.decision = decision;
    }


    public int getIdMinuta() {
        return idMinuta;
    }

    public void setIdMinuta(int idMinuta) {
        this.idMinuta = idMinuta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
