package edu.unam.pooproject.modelo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "reunion", schema = "public")
public class Reunion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "lugar")
    private String lugar;
    @Column(name = "detalles")
    private String detalles;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "horainicio")
    private String horaInicio;
    @Column(name = "horaFin")
    private String horaFin;
    @Column(name = "estado")
    private boolean estado;
    @ManyToMany
    @JoinTable(
            name = "reunion_miembro",
            joinColumns = @JoinColumn(name = "reunion_id"),
            inverseJoinColumns = @JoinColumn(name = "miembro_id")
    )
    private List<Persona> miembros;

    @ManyToMany
    @JoinTable(
            name = "reunion_expediente",
            joinColumns = @JoinColumn(name = "reunion_id"),
            inverseJoinColumns = @JoinColumn(name = "expediente_id")
    )
    private List<Expediente> orden;
    @OneToMany(mappedBy = "reunion")
    private List<Asistencia> asistencias;
    @OneToMany(mappedBy = "reunion")
    private List<Minuta> minutas;

    public Reunion() {
    }

    public Reunion(int nroReunion, LocalDate fecha) {
        this.id = nroReunion;
        this.fecha = fecha;
    }

    public boolean estaCerrado() {
        return this.estado;
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getLugar() {
        return this.lugar;
    }

    public void setLugar(final String lugar) {
        this.lugar = lugar;
    }

    public String getHoraInicio() {
        return this.horaInicio;
    }

    public void setHoraInicio(final String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return this.horaFin;
    }

    public void setHoraFin(final String horaFin) {
        this.horaFin = horaFin;
    }

    public String getDetalles() {
        return this.detalles;
    }

    public void setDetalles(final String detalles) {
        this.detalles = detalles;
    }

    public List<Persona> getMiembros() {
        return this.miembros;
    }

    public void setMiembros(final List<Persona> miembros) {
        this.miembros = miembros;
    }

    public List<Expediente> getOrden() {
        return this.orden;
    }

    public void setOrden(final List<Expediente> orden) {
        this.orden = orden;
    }

    public List<Asistencia> getAsistencia() {
        return this.asistencias;
    }

    public void setAsistencia(final List<Asistencia> asistencia) {
        this.asistencias = asistencia;
    }

    public List<Minuta> getMinutas() {
        return this.minutas;
    }

    public void setMinutas(final List<Minuta> minutas) {
        this.minutas = minutas;
    }

    public int getNroReunion() {
        return id;
    }

    public void setNroReunion(int nroReunion) {
        this.id = nroReunion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        if (estado == false) {
            return "Cerrado";
        } else
            return "Abierto";
    }

    public void setEstado(final boolean estado) {
        this.estado = estado;
    }
}
