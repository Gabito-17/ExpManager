package com.example.pooproject.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reunion", schema = "public")
public class Reunion {
    @Id
    int nroReunion;
    @Temporal(TemporalType.DATE)
    Date fecha;
    @Column
    int orden;

    public Reunion() {
    }

    public Reunion(int nroReunion, Date fecha, int orden) {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
}
