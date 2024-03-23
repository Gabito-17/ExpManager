package edu.unam.pooproject.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reunion", schema = "public")
public class Reunion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDate fecha;

    public Reunion() {
    }

    public Reunion(int nroReunion, LocalDate fecha) {
        this.id = nroReunion;
        this.fecha = fecha;
    }


    public int getNroReunion() {
        return id;
    }

    public void setNroReunion(int nroReunion) {
        this.id = nroReunion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
