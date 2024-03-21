package edu.unam.pooproject;

import edu.unam.pooproject.db.Conexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    //Declaracion del EntityManagerFactory
    public static void main(String[] args) throws Exception {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Crear el EntityManagerFactory
        Conexion.crearEntityManagerFactory();

        //Cargar el Fxml
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/View/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Inicio de Sesión");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        Conexion.cerrarEntityManagerFactory();
    }
}
