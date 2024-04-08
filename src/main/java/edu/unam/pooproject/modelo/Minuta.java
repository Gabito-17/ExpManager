package edu.unam.pooproject.modelo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "minuta", schema = "public")
public class Minuta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "fecha")
    LocalDate fecha;
    @Column(name = "tema")
    String tema;
    @Column(name = "resumen")
    String resumen;
    @ManyToOne
    private Reunion reunion;
    @ManyToMany(mappedBy = "minutas")
    private List<Expediente> expedientes;


    public Minuta() {
    }

    public Minuta(LocalDate f, String t, String res, Reunion reu, Expediente e) {
        this.fecha = fecha;
        this.tema = t;
        this.resumen = res;
        this.reunion = reu;
        this.expedientes.add(e);
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public void setFecha(final LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTema() {
        return this.tema;
    }

    public void setTema(final String tema) {
        this.tema = tema;
    }

    public String getResumen() {
        return this.resumen;
    }

    public void setResumen(final String resumen) {
        this.resumen = resumen;
    }

    public Reunion getReunion() {
        return this.reunion;
    }

    public void setReunion(final Reunion reunion) {
        this.reunion = reunion;
    }

    public List<Expediente> getExpedientes() {
        return this.expedientes;
    }

    public void setExpedientes(final List<Expediente> expedientes) {
        this.expedientes = expedientes;
    }
}
