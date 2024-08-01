package edu.unam.pooproject.controller;

import edu.unam.pooproject.Services.Enrutador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class NavegacionController {

    //Ubicar en ventana "Expediente"
    @FXML
    public void menuExpediente(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/expediente-view.fxml");
    }

    //Ubicar en ventana "Miembro"
    @FXML
    public void menuMiembro(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/miembros-view.fxml");
    }

    //Ubicar en ventana "Reunion"
    @FXML
    public void menuReunion(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/reunion-view.fxml");
    }
    
    //Ubicar en ventana "Persona"
    @FXML
    public void menuPersona(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/personas-view.fxml");
    }
}
