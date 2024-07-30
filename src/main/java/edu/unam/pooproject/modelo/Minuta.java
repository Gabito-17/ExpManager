package edu.unam.pooproject.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "minuta", schema = "public")
public class Minuta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "tema")
    String tema;
    @Column(name = "resumen")
    String resumen;
    @ManyToOne
    private Reunion reunion;
    @ManyToOne
    @JoinColumn(name = "expediente_id")
    private Expediente expediente;


    public Minuta() {
    }

    public Minuta(LocalDate f, String t, String res, Reunion reu, Expediente e) {
        this.tema = t;
        this.resumen = res;
        this.reunion = reu;
        this.expediente = e;
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getTema() {
        return this.tema;
    }

    public void setTema(final String tema) {
        this.tema = tema;
    }

    public String getResumen() {
        return this.resumen;
    }

    public void setResumen(final String resumen) {
        this.resumen = resumen;
    }

    public Reunion getReunion() {
        return this.reunion;
    }

    public void setReunion(final Reunion reunion) {
        this.reunion = reunion;
    }

    public Expediente getExpediente() {
        return this.expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }
}
