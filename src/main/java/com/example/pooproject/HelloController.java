package com.example.pooproject;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.logging.Logger;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    TextField textUser;
    @FXML
    TextField textPassword;
    @FXML
    protected void onHelloButtonClick(ActionEvent event) {
        welcomeText.setText("Welcome to JavaFX Application!");
        loadStage("com/example/pooproject/hello-view.fxml", event);
    }

    private void loadStage(String url, Event event){


        ((Node)(event.getSource())).getScene().getWindow().hide();




    }
}
