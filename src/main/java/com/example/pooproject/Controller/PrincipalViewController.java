package com.example.pooproject.Controller;


import com.example.pooproject.Services.Enrutador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class PrincipalViewController {

    @FXML
    public void cerrarSesion(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/example/pooproject/View/login-view.fxml");
    }
    @FXML
    public void menuInicio(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/example/pooproject/View/principal-view.fxml");
    }
    @FXML
    public void menuExpedientes(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/example/pooproject/View/expediente-view.fxml");
    }
    @FXML
    public void menuMiembros(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/example/pooproject/View/miembros-view.fxml");
    }
    @FXML
    public void menuReunion(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/example/pooproject/View/reunion-view.fxml");
    }
    @FXML
    public void menuAsistencia(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/example/pooproject/View/asistencia-view.fxml");
    }
    @FXML
    public void menuMinuta(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/example/pooproject/View/minuta-view.fxml");
    }
}