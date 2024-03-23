package edu.unam.pooproject.Repositorio;

import edu.unam.pooproject.modelo.Miembro;
import edu.unam.pooproject.modelo.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class PersonaRepositorio implements Serializable {

    private EntityManagerFactory emf;

    public PersonaRepositorio(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Persona persona) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            EntityTransaction etx = em.getTransaction();
            etx.begin();
            em.persist(persona);
            etx.commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Persona persona) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            EntityTransaction etx = em.getTransaction();
            etx.begin();
            em.merge(persona);
            etx.commit();
        } catch (Exception ex) {
            throw new Exception("Error al actualizar el miembro.", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int dni) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            EntityTransaction etx = em.getTransaction();
            etx.begin();
            Miembro miembro = null;
            try {
                miembro = em.getReference(Miembro.class, dni);
                miembro.getDni();
            } catch (Exception ex) {
                throw new Exception("El miembro con dni " + dni + " no existe.", ex);
            }
            em.remove(miembro);
            etx.commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Miembro> findMiembroEntities() {
        return findMiembroEntities(true, -1, -1);
    }

    public List<Miembro> findMiembroEntities(int maxResults, int firstResult) {
        return findMiembroEntities(false, maxResults, firstResult);
    }

    private List<Miembro> findMiembroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Miembro as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Miembro findMiembro(int dni) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Miembro.class, dni);
        } finally {
            em.close();
        }
    }

    public int getMiembroCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Miembro as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
