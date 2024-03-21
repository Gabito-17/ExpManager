package edu.unam.pooproject.modelo;

import java.time.LocalDate;

import javax.persistence.*;


@Entity
@Table(name = "asistencia", schema = "public")
public class Asistencia {

    @Id
    @Column(name = "idAsistencia")
    int idAsistencia;
    @Basic
    @Column(name = "fecha")
    Boolean asiste;

    public Asistencia() {
    }

    public Asistencia(int idAsistencia, LocalDate fecha, Boolean asiste) {
        this.idAsistencia = idAsistencia;
        this.asiste = asiste;
    }


    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }


    public Boolean getAsiste() {
        return asiste;
    }

    public void setAsiste(Boolean asiste) {
        this.asiste = asiste;
    }

}
