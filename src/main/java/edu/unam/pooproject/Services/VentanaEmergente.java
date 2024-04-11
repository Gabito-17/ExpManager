package edu.unam.pooproject.Services;

import javafx.scene.control.Alert;

public class VentanaEmergente {

    public void mostrarError(String s) {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText((String) null);
        alertError.setContentText(s);
        alertError.showAndWait();
    }

    public void mostrarExito(String s) {
        Alert alertExisto = new Alert(Alert.AlertType.INFORMATION);
        alertExisto.setTitle("Exito");
        alertExisto.setHeaderText((String) null);
        alertExisto.setContentText(s);
        alertExisto.showAndWait();
    }
}
