package edu.unam.pooproject.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "minuta", schema = "public")
public class Minuta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "fecha")
    LocalDate fecha;
    @Column(name = "lugar")
    String lugar;
    @Column(name = "resumen")
    String resumen;
    @Column(name = "decision")
    String decision;

    public Minuta() {
    }

    public Minuta(int idMinuta, LocalDate fecha, String lugar, String resumen, String decision) {
        this.id = idMinuta;
        this.fecha = fecha;
        this.lugar = lugar;
        this.resumen = resumen;
        this.decision = decision;
    }


    public int getIdMinuta() {
        return id;
    }

    public void setIdMinuta(int idMinuta) {
        this.id = idMinuta;
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
