package com.example.pooproject.Controller;

import com.example.pooproject.Services.Enrutador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;


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

    //ComboBox "estado" del expediente
    @FXML
    private ComboBox comboBoxEstado;
    ObservableList<String> estadoList = FXCollections.observableArrayList("Alta","Baja");
    //Cargar y mostrar ComboBoxEstado del expediente
    @FXML
    public void mostrarCombo(Event event){
        comboBoxEstado.setItems(estadoList);
    }

    //Limpiar todos los inputs
    @FXML
    public void limpiarCampos(ActionEvent event){
        idExpediente.clear();
        idIniciante.clear();
        fechaIngreso.setValue(null);
        texto.clear();
    }
    public void cargarCampos(ActionEvent event){
        resultadoText.setText(idExpediente.getText()+idIniciante.getText()+texto.getText());
    }

    //Cerrar Sesion y ubicar en ventana "Login"
    @FXML
    public void cerrarSesion(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/example/pooproject/View/login-view.fxml");
    }

    //Ubicar en ventana "Inicio"
    @FXML
    public void menuInicio(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/example/pooproject/View/principal-view.fxml");
    }
    //Ubicar en ventana "Expedientes"
    @FXML
    public void menuExpedientes(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/example/pooproject/View/expediente-view.fxml");
    }
    //Ubicar en ventana "Miembros"
    @FXML
    public void menuMiembros(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/example/pooproject/View/miembros-view.fxml");
    }
    //Ubicar en ventana "Reunion"
    @FXML
    public void menuReunion(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/example/pooproject/View/reunion-view.fxml");
    }
    //Ubicar en ventana "Asistencia"
    @FXML
    public void menuAsistencia(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/example/pooproject/View/asistencia-view.fxml");
    }
    //Ubicar en ventana "Minuta"
    @FXML
    public void menuMinuta(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/example/pooproject/View/minuta-view.fxml");
    }
}

