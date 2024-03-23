package edu.unam.pooproject.modelo;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "asistencia", schema = "public")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDate fecha;
    Boolean asiste;

    public Asistencia() {
    }

    public Asistencia(int idAsistencia, LocalDate fecha, Boolean asiste) {
        this.id = idAsistencia;
        this.asiste = asiste;
    }


    public int getId() {
        return id;
    }

    public void setId(int idAsistencia) {
        this.id = idAsistencia;
    }


    public Boolean getAsiste() {
        return asiste;
    }

    public void setAsiste(Boolean asiste) {
        this.asiste = asiste;
    }

}
