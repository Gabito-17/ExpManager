package edu.unam.pooproject.modelo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persona", schema = "public")
public class Persona {
    @Id
    String dni;
    @Column(name = "nombre")
    String nombre;
    @Column(name = "apellido")
    String apellido;
    @Column(name = "fechanacimiento")
    LocalDate fechaNacimiento;

    @Column(name = "esmiembro")
    boolean esMiembro;
    @Column(name = "email")
    String email;
    @OneToMany(mappedBy = "miembro")
    private List<Asistencia> asistencias;
    @ManyToMany(mappedBy = "miembros")
    private List<Reunion> reuniones;
    @OneToMany(mappedBy = "iniciante")
    private List<Expediente> expedientesIniciados;

    public Persona() {
    }

    public Persona(final String dni, final String nombre, final String apellido,
                   final LocalDate fechaNacimiento, final boolean esMiembro,
                   final String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.esMiembro = esMiembro;
        this.email = email;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String d) {
        this.dni = d;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(final String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(final LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean esMiembro() {
        return this.esMiembro;
    }

    public void setRol(final boolean esMiembro) {
        this.esMiembro = esMiembro;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public List<Reunion> getReuniones() {
        return this.reuniones;
    }

    public void añadirReunion(Reunion reunion) {
        this.reuniones.add(reunion);
    }

    public List<Expediente> getExpedientesIniciados() {
        return this.expedientesIniciados;
    }

    public void iniciarExpediente(Expediente expediente) {
        this.expedientesIniciados.add(expediente);
    }

    public String getNombreCompleto() {
        return apellido + " " + nombre;
    }

    public void setEsMiembro(boolean b) {
        esMiembro = b;
    }
}

