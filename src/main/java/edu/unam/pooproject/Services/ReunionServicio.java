package edu.unam.pooproject.Services;

import edu.unam.pooproject.modelo.Reunion;
import edu.unam.pooproject.repositorio.Repositorio;

import javax.persistence.TypedQuery;
import java.util.List;

public class ReunionServicio {

    private Repositorio repositorio;

    public ReunionServicio(Repositorio p) {
        this.repositorio = p;
    }

    public void agregarReunion(Reunion reunion) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.insertar(reunion);
        this.repositorio.confirmarTransaccion();
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


    public Reunion buscarPorId(String id) {
        // Iniciar una consulta JPA para buscar una persona por su DNI
        TypedQuery<Reunion> query = repositorio.getEntityManager()
                .createQuery("SELECT r FROM reunion r WHERE r.id = :id", Reunion.class);

        // Establecer el parámetro de consulta para el DNI
        query.setParameter("id", id);

        // Obtener el resultado de la consulta (debe ser único ya que el DNI es único)
        List<Reunion> resultados = query.getResultList();

        // Si hay resultados, devuelve el primero (debería ser único); de lo contrario, devuelve null
        return resultados.isEmpty() ? null : resultados.get(0);
    }
}


