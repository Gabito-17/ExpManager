package edu.unam.pooproject.controller;

import edu.unam.pooproject.Services.ExpedienteServicio;
import edu.unam.pooproject.Services.ReunionServicio;
import edu.unam.pooproject.db.Conexion;
import edu.unam.pooproject.repositorio.Repositorio;

public class AccionController extends NavegacionController {


    private Repositorio repositorio;
    private ExpedienteServicio expedienteServicio;
    private ReunionServicio reunionServicio;

    public void initialize() {
        this.repositorio = new Repositorio(Conexion.getEntityManagerFactory());
        this.expedienteServicio = new ExpedienteServicio(this.repositorio);
        this.reunionServicio = new ReunionServicio(this.repositorio);

    }
}
