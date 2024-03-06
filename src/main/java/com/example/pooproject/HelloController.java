package com.example.pooproject;

import com.example.pooproject.Controller.PrincipalViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    TextField textUser;
    @FXML
    TextField textPassword;
    @FXML
    Button botonLogin;
    @FXML
    protected void loginStage(ActionEvent event) {
        System.out.println("Esto funciona");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("principal-view.fxml"));
            Parent root = loader.load();
            PrincipalViewController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controlador.closeWindow());
            closeStage(event);

        }
        catch (IOException ex){
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeStage(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
