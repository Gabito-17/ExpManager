package edu.unam.pooproject.Repositorio;

import edu.unam.pooproject.modelo.Expediente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class ExpedienteRepositorio implements Serializable {
    private EntityManagerFactory emf = null;

    public ExpedienteRepositorio(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Expediente expediente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            EntityTransaction etx = em.getTransaction();
            etx.begin();
            em.persist(expediente);
            etx.commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Expediente expediente) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            EntityTransaction etx = em.getTransaction();
            etx.begin();
            em.merge(expediente);
            etx.commit();
        } catch (Exception ex) {
            throw new Exception("Error al actualizar el expediente.", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int nroExpediente) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            EntityTransaction etx = em.getTransaction();
            etx.begin();
            Expediente expediente;
            try {
                expediente = em.getReference(Expediente.class, nroExpediente);
                expediente.getNroExpediente(); // Lanza una excepción si no se encuentra el expediente
            } catch (Exception ex) {
                throw new Exception("El expediente con el número " + nroExpediente + " no existe.", ex);
            }
            em.remove(expediente);
            etx.commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Expediente> findExpedienteEntities() {
        return findExpedienteEntities(true, -1, -1);
    }

    public List<Expediente> findExpedienteEntities(int maxResults, int firstResult) {
        return findExpedienteEntities(false, maxResults, firstResult);
    }

    private List<Expediente> findExpedienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Expediente as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Expediente findExpediente(String nroExpediente) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Expediente.class, nroExpediente);
        } finally {
            em.close();
        }
    }

    public int getExpedienteCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Expediente as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

