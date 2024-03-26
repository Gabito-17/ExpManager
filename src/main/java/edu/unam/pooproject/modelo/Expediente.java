package edu.unam.pooproject.modelo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "expediente", schema = "public")
public class Expediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "texto")
    String texto;
    @Column(name = "fechaingreso")
    LocalDate fechaIngreso;
    @Column(name = "estado")
    Boolean estado;
    @ManyToOne
    @JoinColumn(name = "iniciante_id")
    private Iniciante iniciante;
    @OneToMany(mappedBy = "expediente")
    private List<Accion> acciones;

    @ManyToMany
    @JoinTable(
            name = "expediente_involucrado",
            joinColumns = @JoinColumn(name = "expediente_id"),
            inverseJoinColumns = @JoinColumn(name = "involucrado_id")
    )
    private List<Involucrado> involucrados;

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
