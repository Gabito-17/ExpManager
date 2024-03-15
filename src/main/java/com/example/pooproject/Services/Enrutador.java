package com.example.pooproject.Services;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Enrutador {

    public static void cambiarVentana(ActionEvent event, String ruta){
    try {
        FXMLLoader loader = new FXMLLoader(Enrutador.class.getResource(ruta));
        Parent root = loader.load();
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (
    IOException e) {
        e.printStackTrace();
    }
    }
}
