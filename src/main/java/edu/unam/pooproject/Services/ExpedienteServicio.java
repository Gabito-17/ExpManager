package edu.unam.pooproject.Services;

import edu.unam.pooproject.modelo.Expediente;
import edu.unam.pooproject.modelo.Persona;
import edu.unam.pooproject.repositorio.Repositorio;

import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class ExpedienteServicio {

    private Repositorio repositorio;

    public ExpedienteServicio(Repositorio r) {
        this.repositorio = r;
    }

    public void agregarExpediente(Expediente expediente) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.insertar(expediente);
        this.repositorio.confirmarTransaccion();
    }

    public void editarExpediente(Expediente expediente) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.modificar(expediente);
        this.repositorio.confirmarTransaccion();
    }

    public void eliminarExpediente(Expediente expediente) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.eliminar(expediente);
        this.repositorio.confirmarTransaccion();
    }

    public List<Expediente> obtenerTodos() {
        return this.repositorio.obtenerTodos(Expediente.class);
    }

    public Expediente buscarPorId(Integer id) {
        return this.repositorio.obtener(Expediente.class, id);
    }

    public void agregarInvolucrados(Expediente expediente, List<Persona> involucrado) {
        this.repositorio.iniciarTransaccion();
        expediente.setInvolucrados(involucrado);
        this.repositorio.modificar(expediente);
        this.repositorio.confirmarTransaccion();
    }

    public List<Persona> obtenerInvolucrados(Expediente expedienteSeleccionado) {
        if (expedienteSeleccionado != null) {
            // Crear una consulta para obtener los miembros de la reunión seleccionada
            TypedQuery<Persona> query = repositorio.getEntityManager()
                    .createQuery("SELECT ex FROM Expediente e JOIN e.involucrados ex WHERE e = :expediente", Persona.class)
                    .setParameter("expediente", expedienteSeleccionado);

            // Ejecutar la consulta y obtener la lista de miembros
            List<Persona> miembros = query.getResultList();

            // Devolver la lista de miembros
            return miembros;
        }
        return Collections.emptyList(); // Devuelve una lista vacía si la reunión seleccionada es nula
    }
}



