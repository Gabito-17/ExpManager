package edu.unam.pooproject.Services;

import edu.unam.pooproject.modelo.Expediente;
import edu.unam.pooproject.modelo.Persona;
import edu.unam.pooproject.repositorio.Repositorio;

import javax.persistence.TypedQuery;
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
        TypedQuery<Expediente> query = repositorio.getEntityManager().createQuery("Select p From Expediente p WHERE p.id = :id", Expediente.class);
        query.setParameter("id", id);
        List<Expediente> resultados = query.getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    public void agregarInvolucrados(Expediente expediente, List<Persona> involucrado) {
        this.repositorio.iniciarTransaccion();
        expediente.setInvolucrados(involucrado);
        this.repositorio.modificar(expediente);
        this.repositorio.confirmarTransaccion();

    }
}



