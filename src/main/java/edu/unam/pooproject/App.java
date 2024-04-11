package edu.unam.pooproject;

import edu.unam.pooproject.db.Conexion;
import edu.unam.pooproject.repositorio.Repositorio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
    public static void main(String[] args) throws Exception {
        Conexion.getEntityManagerFactory();
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Repositorio repositorio = new Repositorio(Conexion.getEntityManagerFactory());
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/View/miembros-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Inicio de Sesión");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Conexion.getEntityManagerFactory().close();
    }
}