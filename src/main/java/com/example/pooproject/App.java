package com.example.pooproject;

import com.example.pooproject.Persistencia.ControladoraPersistencia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) {
        ControladoraPersistencia controlpersis = new ControladoraPersistencia();
        
        launch();

    }

    @Override
    public void start(Stage stage) throws IOException {
        //Connection conexion = ConexionBaseDatos.conectar();

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/example/pooproject/View/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Inicio de Sesión");
        stage.setScene(scene);
        stage.show();

        /*try {
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM prueba");
            for (int i = 0; i <= 2; i++) {
                if (resultSet.next()) {
                    System.out.println(resultSet.getInt("edad"));
                    System.out.println(resultSet.getString("nombre"));
                    System.out.println(resultSet.getString("apellido"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }
}