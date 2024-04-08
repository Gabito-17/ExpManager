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

    public boolean esIniciante(Persona personaSeleccionada) {
        // Crear una consulta JPA para verificar si la persona tiene expedientes asociados
        TypedQuery<Long> query = repositorio.getEntityManager()
                .createQuery("SELECT COUNT(e) FROM Expediente e WHERE e.iniciante = :persona", Long.class);

        // Establecer el parámetro de consulta para la persona seleccionada
        query.setParameter("persona", personaSeleccionada);

        // Obtener el resultado de la consulta (cantidad de expedientes asociados a la persona)
        Long cantidadExpedientes = query.getSingleResult();

        // Si la cantidad de expedientes es mayor que cero, significa que la persona tiene expedientes asociados
        return cantidadExpedientes > 0;
    }
}

