package edu.unam.pooproject.Controller;

import edu.unam.pooproject.Repositorio.ExpedienteJPAController;
import edu.unam.pooproject.Services.Enrutador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ExpedienteController {
    //Informacion del expediente
    @FXML
    TextField idExpediente;
    @FXML
    TextField idIniciante;
    @FXML
    DatePicker fechaIngreso;
    @FXML
    Label resultadoText;
    @FXML
    TextArea texto;
    ObservableList<String> estadoList = FXCollections.observableArrayList("Alta", "Baja");
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PooProject");
    ExpedienteJPAController control = new ExpedienteJPAController(emf);

    //ComboBox "estado" del expediente
    @FXML
    private ComboBox comboBoxEstado;

    //Cargar y mostrar ComboBoxEstado del expediente
    @FXML
    public void mostrarCombo(Event event) {
        comboBoxEstado.setItems(estadoList);
    }

    //Limpiar todos los inputs
    @FXML
    public void limpiarCampos(ActionEvent event) {
        idExpediente.clear();
        idIniciante.clear();
        fechaIngreso.setValue(null);
        texto.clear();
    }

    public void cargarCampos(ActionEvent event) throws Exception {
        resultadoText.setText(idExpediente.getText() + idIniciante.getText() + texto.getText());
        control.destroy(1);
    }

    //Cerrar Sesion y ubicar en ventana "Login"
    @FXML
    public void cerrarSesion(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/login-view.fxml");
    }

    //Ubicar en ventana "Inicio"
    @FXML
    public void menuInicio(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/principal-view.fxml");
    }

    //Ubicar en ventana "Expedientes"
    @FXML
    public void menuExpedientes(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/expediente-view.fxml");
    }

    //Ubicar en ventana "Miembros"
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

