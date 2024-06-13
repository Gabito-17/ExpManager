package edu.unam.pooproject.Services;

import edu.unam.pooproject.modelo.Asistencia;
import edu.unam.pooproject.modelo.Expediente;
import edu.unam.pooproject.modelo.Persona;
import edu.unam.pooproject.modelo.Reunion;
import edu.unam.pooproject.repositorio.Repositorio;

import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class AsistenciaServicio {

    private Repositorio repositorio;

    public AsistenciaServicio(Repositorio p) {
        this.repositorio = p;
    }

    public void crearAsistencia(Reunion reunion, List<Persona> miembros) {
        for (Persona m : miembros) {
            Asistencia asistencia = new Asistencia();
            asistencia.setReunion(reunion);
            asistencia.setMiembro(m);
            asistencia.setFueCargado(false);
            this.repositorio.iniciarTransaccion();
            this.repositorio.insertar(asistencia);
            this.repositorio.confirmarTransaccion();
        }


    }

    public void editarReunion(Reunion reunion) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.modificar(reunion);
        this.repositorio.confirmarTransaccion();
    }

    public void eliminarReunion(Reunion reunion) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.eliminar(reunion);
        this.repositorio.confirmarTransaccion();
    }

    public List<Reunion> obtenerTodos() {
        return this.repositorio.obtenerTodos(Reunion.class);
    }

    public List<Persona> obtenerMiembrosDeReunion(Reunion reunionSeleccionada) {
        if (reunionSeleccionada != null) {
            // Crear una consulta para obtener los miembros de la reunión seleccionada
            TypedQuery<Persona> query = repositorio.getEntityManager()
                    .createQuery("SELECT rm FROM Reunion r JOIN r.miembros rm WHERE r = :reunion", Persona.class)
                    .setParameter("reunion", reunionSeleccionada);

            // Ejecutar la consulta y obtener la lista de miembros
            List<Persona> miembros = query.getResultList();

            // Devolver la lista de miembros
            return miembros;
        }
        return Collections.emptyList(); // Devuelve una lista vacía si la reunión seleccionada es nula
    }


    public Reunion buscarPorId(int id) {
        return this.repositorio.obtener(Reunion.class, id);
    }

    public List<Expediente> obtenerExpedientes(Reunion reunionSeleccionada) {
        if (reunionSeleccionada != null) {
            // Crear una consulta para obtener el orden de expedientes que se trataran o trataron en la reunion seleccionada.
            TypedQuery<Expediente> query = repositorio.getEntityManager()
                    .createQuery("SELECT e FROM Reunion r JOIN r.expedientes e WHERE r = :reunion", Expediente.class)
                    .setParameter("reunion", reunionSeleccionada);

            // Ejecutar la consulta y obtener la lista de expedientes
            List<Expediente> expedientes = query.getResultList();

            // Devolver la lista de expedientes
            return expedientes;
        }
        return Collections.emptyList(); // Devuelve una lista vacía si la reunión seleccionada es nula
    }

}


