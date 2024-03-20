package edu.unam.pooproject;

import edu.unam.pooproject.Repositorio.ExpedienteJPAController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App extends Application {
    public static void main(String[] args) throws Exception {

        launch();

    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/View/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Inicio de Sesión");
        stage.setScene(scene);
        stage.show();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PooProject");
        ExpedienteJPAController control = new ExpedienteJPAController(emf);
        //Expediente exp = new Expediente(3, "hola", "String texto", new Date(), false);
        //Expediente exp1 = new Expediente(4, "hola", "String texto", new Date(), true);
        //control.create(exp);
        //control.create(exp1);

    }
}