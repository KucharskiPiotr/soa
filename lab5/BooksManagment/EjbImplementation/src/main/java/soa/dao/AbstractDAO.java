package soa.dao;

import soa.ejb.dto.AbstractDTO;
import soa.ejb.dto.BookData;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public abstract class AbstractDAO<T extends AbstractDTO> {

    private static final String PERSISTENCE_UNIT_NAME = "AGH-MySQL";

    protected EntityManager entityManager;

    private String className;

    protected AbstractDAO(Class clazz) {
        className = clazz.getSimpleName();
        entityManager = Persistence
                .createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .createEntityManager();
    }

    public List<T> getItems() {
        Query query = entityManager.createQuery("FROM " + className);
        return query.getResultList();
    }

    public T getItem(Integer itemId) {
        Query query = entityManager.createQuery("FROM " + className + "c WHERE c.id = :id");
        query.setParameter("id", itemId);
        return (T) query.getSingleResult();
    }

    public void addItem(T item) {
        try {
            entityManager.persist(item);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void updateItem(T item) {
        entityManager.merge(item);
        entityManager.getTransaction().commit();
    }

    public void deleteItem(T item) {
        entityManager.remove(entityManager.contains(item) ? item : entityManager.merge(item));
        entityManager.getTransaction().commit();
    }

    public void deleteItem(Integer itemId) {
        T item = getItem(itemId);
        deleteItem(item);
    }

}
