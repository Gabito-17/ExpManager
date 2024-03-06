package com.example.pooproject;

import com.example.pooproject.db.ConexionBaseDatos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.pooproject.db.ConexionBaseDatos.conectar;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Inicio de Sesión");
        stage.setScene(scene);
        stage.show();
        Connection conexion = ConexionBaseDatos.conectar();


        try {
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM prueba");
            for (int i = 0; i <= 2; i++) {
                if (resultSet.next()) {
                    System.out.println(resultSet.getInt("edad"));
                    System.out.println(resultSet.getString("nombre"));
                    System.out.println(resultSet.getString("apellido"));
                }
            }
        }

        catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch();
    }
}