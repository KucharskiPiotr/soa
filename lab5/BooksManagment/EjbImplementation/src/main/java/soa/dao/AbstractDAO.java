package soa.dao;

import soa.ejb.dto.AbstractDTO;
import soa.ejb.dto.BookData;
import soa.ejb.dto.BorrowData;
import soa.ejb.utils.SearchCriteria;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T extends AbstractDTO> {

    private static final String PERSISTENCE_UNIT_NAME = "AGH-MySQL";

    protected EntityManager entityManager;

    private String className;

    private Class clazz;

    protected AbstractDAO(Class clazz) {
        this.clazz = clazz;
        className = clazz.getSimpleName();
        entityManager = Persistence
                .createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .createEntityManager();
    }

    public List<T> getItems() {
        TypedQuery query = entityManager.createQuery("SELECT data FROM " + className + " data", clazz);
        return query.getResultList();
    }

    public T getItem(Integer itemId) {
        TypedQuery query = entityManager.createQuery("SELECT c FROM " + className + " c WHERE c.id = :id", clazz);
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
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
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

    public List<T> searchItems(SearchCriteria searchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
        Root<T> b = query.from(clazz);
        query.select(b);
        List<Predicate> constraints = new ArrayList<>();

        addBorrowConstraints(searchCriteria, criteriaBuilder, b, constraints);
        addCustomerConstrains(searchCriteria, criteriaBuilder, b, constraints);
        addAuthorConstraints(searchCriteria, criteriaBuilder, b, constraints);
        addBookConstraints(searchCriteria, criteriaBuilder, b, constraints);

        Predicate[] constraintsTab =  new Predicate[constraints.size()];
        constraints.toArray(constraintsTab);
        query.where(constraintsTab);

        TypedQuery<T> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    protected abstract void addBookConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<T> entity, List<Predicate> constraints);
    protected abstract void addBorrowConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<T> entity, List<Predicate> constraints);
    protected abstract void addCustomerConstrains(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<T> entity, List<Predicate> constraints);
    protected abstract void addAuthorConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<T> entity, List<Predicate> constraints);
}
