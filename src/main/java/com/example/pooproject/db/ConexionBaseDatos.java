package com.example.pooproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Clase intermediearia para la conexion a la base de datos.
public class ConexionBaseDatos {
    private static final String URL = "jdbc:postgresql://localhost:5432/prueba";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Gabito1235";


    public static Connection conectar() {
        //Establece la conexion con la base de datos.
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos!");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }
}
