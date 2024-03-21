package edu.unam.pooproject.Controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.unam.pooproject.Repositorio.MiembroRepositorio;
import edu.unam.pooproject.Services.Enrutador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MiembroController {
    
    //Informacion del miembro
    @FXML
    TextField txtDniMiembro;
    @FXML
    TextField txtNombreMiembro;
    @FXML
    TextField txtApellidoMiembro;
    @FXML
    TextField txtEmailMiembro;
    @FXML
    Label lblResultado;
    @FXML
    TextArea txtTexto;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PooProject");
    MiembroRepositorio control = new MiembroRepositorio(emf);
    
    //Limpiar todos los inputs
    @FXML
    public void limpiarCampos() {
        txtDniMiembro.clear();
        txtNombreMiembro.clear();
        txtApellidoMiembro.clear();
        txtEmailMiembro.clear();
        txtTexto.clear();
    }

    public void cargarCampos() throws Exception {
        lblResultado.setText(txtDniMiembro.getText() + txtNombreMiembro.getText() + txtTexto.getText());
        control.destroy(3);
    }

    //Cerrar Sesion y ubicar en ventana "Login"
    @FXML
    public void cerrarSesion(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/login-view.fxml");
    }

    //Ubicar en ventana "Inicio"
    @FXML
    public void menuInicio(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/inicio-view.fxml");
    }

    //Ubicar en ventana "Expediente"
    @FXML
    public void menuExpediente(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/expediente-view.fxml");
    }

    //Ubicar en ventana "Miembro"
    @FXML
    public void menuMiembros(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/miembros-view.fxml");
    }

    //Ubicar en ventana "Reunion"
    @FXML
    public void menuReunion(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/reunion-view.fxml");
    }

    //Ubicar en ventana "Asistencia"
    @FXML
    public void menuAsistencia(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/asistencia-view.fxml");
    }

    //Ubicar en ventana "Minuta"
    @FXML
    public void menuMinuta(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/minuta-view.fxml");
    }



}
