//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package edu.unam.pooproject.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

public class Repositorio {

    private final EntityManager em;

    public Repositorio(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void iniciarTransaccion() {
        this.em.getTransaction().begin();
    }

    public void confirmarTransaccion() {
        this.em.getTransaction().commit();
    }

    public void descartarTransaccion() {
        this.em.getTransaction().rollback();
    }

    public void insertar(Object o) {
        this.em.persist(o);
    }

    public void modificar(Object o) {
        this.em.merge(o);
    }

    public void eliminar(Object o) {
        this.em.remove(o);
    }

    public void refrescar(Object o) {
        this.em.refresh(o);
    }

    public <T> T obtener(Class<T> clase, Object id) {
        return this.em.find(clase, id);
    }

    public <T> List<T> obtenerTodos(Class<T> clase) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<T> consulta = cb.createQuery(clase);
        Root<T> origen = consulta.from(clase);
        consulta.select(origen);
        return this.em.createQuery(consulta).getResultList();
    }

    public <T, P> List<T> obtenerTodosOrdenadosPor(Class<T> clase, SingularAttribute<T, P> orden) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<T> consulta = cb.createQuery(clase);
        Root<T> origen = consulta.from(clase);
        consulta.select(origen);
        consulta.orderBy(new Order[]{cb.asc(origen.get(orden))});
        return this.em.createQuery(consulta).getResultList();
    }
}
