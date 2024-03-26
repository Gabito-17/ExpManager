package edu.unam.pooproject.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persona", schema = "public")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int dni;
    @Column(name = "nombre")
    String nombre;
    @Column(name = "apellido")
    String apellido;
    @Column(name = "fechanacimiento")
    LocalDate fechaNacimiento;
    @Column(name = "email")
    String email;

    public Persona() {
    }

    public Persona(final int dni, final String nombre, final String apellido, final LocalDate fechaNacimiento, final String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
    }

    public int getDni() {
        return this.dni;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
