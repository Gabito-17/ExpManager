package edu.unam.pooproject;

import edu.unam.pooproject.db.Conexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        Conexion.crearEntityManagerFactory();
        launch();

    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/View/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Inicio de Sesión");
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if (Conexion.getEntityManagerFactory() != null && Conexion.getEntityManagerFactory().isOpen()) {
            Conexion.getEntityManagerFactory().close(); // Cierra el EMF
        }
    }
}