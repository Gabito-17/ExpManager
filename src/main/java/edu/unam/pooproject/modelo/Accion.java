package edu.unam.pooproject.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "accion", schema = "public")

public class Accion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "fecha")
    LocalDate fecha;
    @Column(name = "cambio")
    String cambio;
    @ManyToOne
    @JoinColumn(name = "expediente_id")
    private Expediente expediente;

    public Accion() {
    }

    public Accion(final LocalDate f, final String c, final Expediente e) {
        this.fecha = f;
        this.cambio = c;
        this.expediente = e;
    }
}
