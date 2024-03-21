package edu.unam.pooproject.Controller;

import edu.unam.pooproject.Services.Enrutador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LoginController {
    @FXML
    TextField textUser;
    @FXML
    TextField textPassword;
    @FXML
    private Label welcomeText;
    @FXML
    private Label errorText;

    @FXML
    //Abrir la ventana Principal, cerrar la ventana Login
    protected void menuInicio(ActionEvent event) {
        //if (textPassword.getText().toLowerCase().equals("admin") & textUser.getText().toLowerCase().equals("admin")) {
        Enrutador.cambiarVentana(event, "/View/principal-view.fxml");
        System.out.println("Redireccionando al menu inicio");
    } //else {

    // errorText.setText("Usuario o contraseña invalidos");
    // textPassword.setText("");
    // textUser.setText("");
    // }
    //}
    public void closeStage(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
