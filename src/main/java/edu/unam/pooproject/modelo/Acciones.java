package edu.unam.pooproject.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "acciones", schema = "public")

public class Acciones {
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

    
    public Acciones() {
    }

    public Acciones(Date fecha, String cambio) {
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
