package edu.unam.pooproject.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Conexion {
    private static EntityManagerFactory emf = null;

    public static void crearEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("PooProject");
        }
    }

    public static void cerrarEntityManagerFactory() {
        if (emf != null) {
            emf.close();
        }
    }

    public static EntityManager crearEntityManager() {
        return emf.createEntityManager();
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }


}
