package edu.unam.pooproject.Services;

import edu.unam.pooproject.modelo.Persona;
import edu.unam.pooproject.repositorio.Repositorio;

import javax.persistence.TypedQuery;
import java.util.List;

public class PersonaServicio {

    private Repositorio repositorio;

    public PersonaServicio(Repositorio p) {
        this.repositorio = p;
    }

    public void agregarPersona(Persona persona) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.insertar(persona);
        this.repositorio.confirmarTransaccion();
    }

    public void editarPersona(Persona persona) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.modificar(persona);
        this.repositorio.confirmarTransaccion();
    }

    public void eliminarPersona(Persona persona) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.eliminar(persona);
        this.repositorio.confirmarTransaccion();
    }

    public List<Persona> obtenerTodos() {
        return this.repositorio.obtenerTodos(Persona.class);
    }


    public Persona buscarPorDni(String dni) {
        // Iniciar una consulta JPA para buscar una persona por su DNI
        TypedQuery<Persona> query = repositorio.getEntityManager()
                .createQuery("SELECT p FROM Persona p WHERE p.dni = :dni", Persona.class);

        // Establecer el parámetro de consulta para el DNI
        query.setParameter("dni", dni);

        // Obtener el resultado de la consulta (debe ser único ya que el DNI es único)
        List<Persona> resultados = query.getResultList();

        // Si hay resultados, devuelve el primero (debería ser único); de lo contrario, devuelve null
        return resultados.isEmpty() ? null : resultados.get(0);
    }
}

