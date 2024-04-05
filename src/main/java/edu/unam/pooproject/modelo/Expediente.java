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
    @Column(name = "titulo")
    String titulo;
    @Column(name = "nota")
    String nota;
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
    @ManyToMany
    @JoinTable(
            name = "expediente_minuta",
            joinColumns = @JoinColumn(name = "expediente_id"),
            inverseJoinColumns = @JoinColumn(name = "minuta_id")
    )
    private List<Minuta> minutas;

    public Expediente() {
    }

    public Expediente(final String nota, final LocalDate fechaIngreso, final Boolean estado, final Iniciante iniciante) {
        this.nota = nota;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
        this.iniciante = iniciante;
    }

    public void setIniciante(final Iniciante iniciante) {
        this.iniciante = iniciante;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getId() {
        return id;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
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
