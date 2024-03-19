package com.example.pooproject.modelo;

public class Involucrado {
    int idInvolucrado;
    String nombre;

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
