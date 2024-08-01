package edu.unam.pooproject.modelo;

import javax.persistence.*;


@Entity
@Table(name = "asistencia", schema = "public")
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "asiste")
    Boolean asistio;
    @Column(name = "fueCargado")
    Boolean fueCargado;
    @ManyToOne
    @JoinColumn(name = "reunion_id")
    private Reunion reunion;
    @ManyToOne
    @JoinColumn(name = "miembro_id")
    private Persona miembro;

    public Asistencia() {
    }

    public Asistencia(Boolean a, Reunion r, Persona m) {
        this.asistio = a;
        this.reunion = r;
        this.miembro = m;
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Boolean getFueCargado() {
        return this.fueCargado;
    }

    public void setFueCargado(Boolean b) {
        this.fueCargado = b;
    }

    public Boolean getAsistio() {
        return this.asistio;
    }

    public void setAsistio(final Boolean asistio) {
        this.asistio = asistio;
    }

    public Reunion getReunion() {
        return this.reunion;
    }

    public void setReunion(final Reunion reunion) {
        this.reunion = reunion;
    }

    public Persona getMiembro() {
        return this.miembro;
    }

    public void setMiembro(final Persona miembro) {
        this.miembro = miembro;
    }

    public String getAsisteString() {
        if (asistio == null) {
            return "Cargar";
        }
        return asistio ? "Presente" : "Ausente";
    }
}
