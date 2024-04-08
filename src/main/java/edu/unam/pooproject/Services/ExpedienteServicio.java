package edu.unam.pooproject.Services;

import edu.unam.pooproject.modelo.Expediente;
import edu.unam.pooproject.repositorio.Repositorio;

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
}

