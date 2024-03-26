package edu.unam.pooproject.modelo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "reunion", schema = "public")
public class Reunion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "fecha")
    LocalDate fecha;
    @OneToMany(mappedBy = "reunion")
    private List<Asistencia> asistencias;

    @ManyToMany
    @JoinTable(
            name = "reunion_miembro",
            joinColumns = @JoinColumn(name = "reunion_id"),
            inverseJoinColumns = @JoinColumn(name = "miembro_id")
    )
    private List<Miembro> miembros;

    public Reunion() {
    }

    public Reunion(int nroReunion, LocalDate fecha) {
        this.id = nroReunion;
        this.fecha = fecha;
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

}
