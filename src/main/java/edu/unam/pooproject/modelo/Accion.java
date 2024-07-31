package edu.unam.pooproject.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "accion", schema = "public")

public class Accion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "fecha")
    LocalDate fecha;
    @Column(name = "Titulo")
    String Titulo;
    @Column(name = "Accion")
    String Accion;
    @ManyToOne
    @JoinColumn(name = "expediente_id")
    private Expediente expediente;
    public Accion(LocalDate fecha, String titulo, String accion, Expediente expediente) {
        this.fecha = fecha;
        Titulo = titulo;
        Accion = accion;
        this.expediente = expediente;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public String getTitulo() {
        return Titulo;
    }
    public void setTitulo(String titulo) {
        Titulo = titulo;
    }
    public String getAccion() {
        return Accion;
    }
    public void setAccion(String accion) {
        Accion = accion;
    }
    public Expediente getExpediente() {
        return expediente;
    }
    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    
}
