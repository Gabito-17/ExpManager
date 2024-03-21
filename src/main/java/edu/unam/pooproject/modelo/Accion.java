package edu.unam.pooproject.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "accion", schema = "public")

public class Accion {
    @Id
    @Column(name = "idAccion")
    int idAccion;
    @Basic
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    Date fecha;
    @Basic
    @Column(name = "cambio")
    String cambio;


    public Accion() {
    }

    public Accion(Date fecha, String cambio) {
        this.fecha = fecha;
        this.cambio = cambio;
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }
}
