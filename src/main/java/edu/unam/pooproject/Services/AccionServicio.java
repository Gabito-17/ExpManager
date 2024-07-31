package edu.unam.pooproject.Services;

import edu.unam.pooproject.modelo.Accion;
import edu.unam.pooproject.modelo.Expediente;
import edu.unam.pooproject.repositorio.Repositorio;

import javax.persistence.TypedQuery;
import java.util.List;

public class AccionServicio {

    private Repositorio repositorio;

    public AccionServicio(Repositorio p) {
        this.repositorio = p;
    }

    public void agregarAccion(Accion accion) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.insertar(accion);
        this.repositorio.confirmarTransaccion();
    }

    public void editarMinuta(Accion accion) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.modificar(accion);
        this.repositorio.confirmarTransaccion();
    }
    public void eliminarAccion(Accion accion) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.eliminar(accion);
        this.repositorio.confirmarTransaccion();
    }

    public List<Accion> obtenerTodos() {
        return this.repositorio.obtenerTodos(Accion.class);
    }


    public Accion buscarPorId(Integer id) {
        // Iniciar una consulta JPA para buscar una Accion por su ID
        TypedQuery<Accion> query = repositorio.getEntityManager()
                .createQuery("SELECT p FROM Accion p WHERE p.id = :id", Accion.class);

        // Establecer el parámetro de consulta para el ID de la Accion
        query.setParameter("id", id);

        // Obtener el resultado de la consulta (debe ser único ya que el ID es único)
        List<Accion> resultados = query.getResultList();

        // Si hay resultados, devuelve el primero (debería ser único); de lo contrario, devuelve null
        return resultados.isEmpty() ? null : resultados.get(0);
    }
    public Expediente obtenerExpediente(Integer id) {
        // Iniciar una consulta JPA para obtener el expediente de una minuta por su ID
        TypedQuery<Expediente> query = repositorio.getEntityManager()
                .createQuery("SELECT m.expediente FROM Accion m WHERE m.id = :id", Expediente.class);
    
        // Establecer el parámetro de consulta para el ID de la minuta
        query.setParameter("id", id);
    
        // Obtener el resultado de la consulta (debe ser único ya que el ID es único)
        List<Expediente> resultados = query.getResultList();
    
        // Si hay resultados, devuelve el primero (debería ser único); de lo contrario, devuelve null
        return resultados.isEmpty() ? null : resultados.get(0);
    }
}

