package edu.unam.pooproject.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "accion", schema = "public")

public class Accion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    LocalDate fecha;

    String cambio;

    public Accion() {
    }

    public Accion(final LocalDate fecha, final String cambio, final Expediente expediente) {
        this.fecha = fecha;
        this.cambio = cambio;
    }
}
