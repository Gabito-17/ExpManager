package edu.unam.pooproject.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expediente", schema = "public")
public class Expediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String texto;
    LocalDate fechaIngreso;
    Boolean estado;
    @ManyToOne
    @JoinColumn(name = "iniciante_id")
    private Iniciante iniciante;

    public Expediente() {
    }

    public Expediente(final String texto, final LocalDate fechaIngreso, final Boolean estado, final Iniciante iniciante) {
        this.texto = texto;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
        this.iniciante = iniciante;
    }

    public void setIniciante(final Iniciante iniciante) {
        this.iniciante = iniciante;
    }

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
