package edu.unam.pooproject.modelo;

import javax.persistence.*;

@Entity
@Table(name = "involucrados", schema = "public")
public class Involucrado {
    @Id
    @Column(name = "idInvolucrado")
    int idInvolucrado;
    @Basic
    @Column(name = "nombre")
    String nombre;
    @Column(name = "apellido")
    String apellido;

    public Involucrado() {
    }

    public Involucrado(int idInvolucrado, String nombre) {
        this.idInvolucrado = idInvolucrado;
        this.nombre = nombre;
    }


    public int getIdInvolucrado() {
        return idInvolucrado;
    }

    public void setIdInvolucrado(int idInvolucrado) {
        this.idInvolucrado = idInvolucrado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
