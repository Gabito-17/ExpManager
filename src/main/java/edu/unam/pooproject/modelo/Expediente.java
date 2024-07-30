package edu.unam.pooproject.modelo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "expediente", schema = "public")
public class Expediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "nota")
    private String nota;
    @Column(name = "fechaingreso")
    private LocalDate fechaIngreso;
    @Column(name = "estado")
    private Boolean estado;
    @ManyToOne
    @JoinColumn(name = "iniciante_id")
    private Persona iniciante;
    @ManyToMany(mappedBy = "orden")
    private List<Reunion> reuniones;
    @OneToMany(mappedBy = "expediente")
    private List<Accion> acciones;

    @ManyToMany
    @JoinTable(
            name = "expediente_involucrado",
            joinColumns = @JoinColumn(name = "expediente_id"),
            inverseJoinColumns = @JoinColumn(name = "involucrado_id")
    )
    private List<Persona> involucrados;
    @OneToMany(mappedBy = "expediente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Minuta> minutas = new ArrayList<>();

    public Expediente() {
    }

    public Expediente(final String nota, final LocalDate fechaIngreso, final Boolean estado, final Persona iniciante, final List<Persona> involucrados) {
        this.nota = nota;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
        this.iniciante = iniciante;
        this.involucrados = involucrados;
    }

    public Integer getId() {
        return this.id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public String getNota() {
        return this.nota;
    }

    public void setNota(final String nota) {
        this.nota = nota;
    }

    public LocalDate getFechaIngreso() {
        return this.fechaIngreso;
    }

    public void setFechaIngreso(final LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Boolean getEstado() {
        return this.estado;
    }

    public void setEstado(final Boolean estado) {
        this.estado = estado;
    }

    public Persona getIniciante() {
        return this.iniciante;
    }

    public void setIniciante(final Persona iniciante) {
        this.iniciante = iniciante;
    }

    public List<Reunion> getReuniones() {
        return this.reuniones;
    }

    public void setReuniones(final List<Reunion> reuniones) {
        this.reuniones = reuniones;
    }

    public List<Accion> getAcciones() {
        return this.acciones;
    }

    public void setAcciones(final List<Accion> acciones) {
        this.acciones = acciones;
    }

    public List<Persona> getInvolucrados() {
        return this.involucrados;
    }

    public void setInvolucrados(final List<Persona> involucrados) {
        this.involucrados = involucrados;
    }

    public List<Minuta> getMinutas() {
        return this.minutas;
    }

    public void setMinutas(final List<Minuta> minutas) {
        this.minutas = minutas;
    }

    public List<String> getInvolucradosPorNombre() {

        List<String> names = new ArrayList<>();
        for (Persona persona : involucrados) {
            names.add(persona.getNombre() + " " + persona.getApellido());
        }

        return names;

    }
}
