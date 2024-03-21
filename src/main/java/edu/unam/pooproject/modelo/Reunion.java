package edu.unam.pooproject.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reuniones", schema = "public")
public class Reunion {
    @Id
    @Column(name = "idReunion")
    int idReunion;
    @Temporal(TemporalType.DATE)
    Date fecha;

    public Reunion() {
    }

    public Reunion(int nroReunion, Date fecha) {
        this.idReunion = nroReunion;
        this.fecha = fecha;
    }


    public int getNroReunion() {
        return idReunion;
    }

    public void setNroReunion(int nroReunion) {
        this.idReunion = nroReunion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /*public List<Expediente> getOrden() {
        return orden;
    }

    public void setOrden(List<Expediente> orden) {
        this.orden = orden;
    }*/
}
