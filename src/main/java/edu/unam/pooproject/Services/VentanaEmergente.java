package edu.unam.pooproject.Services;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

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

    public Optional<ButtonType> mostrarConfirmacion(String p, String r) {
        // Mostrar un Alert de confirmación
        Alert alertConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmacion.setTitle("Confirmar");
        alertConfirmacion.setHeaderText(p);
        alertConfirmacion.setContentText(r);
        Optional<ButtonType> resultado = alertConfirmacion.showAndWait();
        return resultado;
    }
}
