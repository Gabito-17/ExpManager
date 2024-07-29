package edu.unam.pooproject.Services;

import edu.unam.pooproject.modelo.Minuta;
import edu.unam.pooproject.modelo.Expediente;
import edu.unam.pooproject.repositorio.Repositorio;

import javax.persistence.TypedQuery;
import java.util.List;

public class MinutaServicio {

    private Repositorio repositorio;

    public MinutaServicio(Repositorio p) {
        this.repositorio = p;
    }

    public void cargarMinuta(Minuta minuta) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.insertar(minuta);
        this.repositorio.confirmarTransaccion();
    }

    public void editarMinuta(Minuta minuta) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.modificar(minuta);
        this.repositorio.confirmarTransaccion();
    }

    public List<Minuta> obtenerTodos() {
        return this.repositorio.obtenerTodos(Minuta.class);
    }


    public Minuta buscarPorId(Integer id) {
        // Iniciar una consulta JPA para buscar una minuta por su ID
        TypedQuery<Minuta> query = repositorio.getEntityManager()
                .createQuery("SELECT p FROM Minuta p WHERE p.id = :id", Minuta.class);

        // Establecer el parámetro de consulta para el ID de la minuta
        query.setParameter("id", id);

        // Obtener el resultado de la consulta (debe ser único ya que el ID es único)
        List<Minuta> resultados = query.getResultList();

        // Si hay resultados, devuelve el primero (debería ser único); de lo contrario, devuelve null
        return resultados.isEmpty() ? null : resultados.get(0);
    }
    public Expediente obtenerExpediente(Integer id) {
        // Iniciar una consulta JPA para obtener el expediente de una minuta por su ID
        TypedQuery<Expediente> query = repositorio.getEntityManager()
                .createQuery("SELECT m.expediente FROM Minuta m WHERE m.id = :id", Expediente.class);
    
        // Establecer el parámetro de consulta para el ID de la minuta
        query.setParameter("id", id);
    
        // Obtener el resultado de la consulta (debe ser único ya que el ID es único)
        List<Expediente> resultados = query.getResultList();
    
        // Si hay resultados, devuelve el primero (debería ser único); de lo contrario, devuelve null
        return resultados.isEmpty() ? null : resultados.get(0);
    }
}

