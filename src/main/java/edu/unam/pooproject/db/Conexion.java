package edu.unam.pooproject.db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexion {
    private static EntityManagerFactory emf = null;

    // Constructor privado para evitar instanciación externa
    private Conexion() {
    }

    // Método estático para obtener la única instancia de EntityManagerFactory
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            crearConexion();
        }
        return emf;
    }

    // Método privado para crear la conexión si no existe
    private static void crearConexion() {
        emf = Persistence.createEntityManagerFactory("PooProject");
    }
}
