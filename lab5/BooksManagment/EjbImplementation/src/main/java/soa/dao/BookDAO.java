package soa.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import soa.ejb.dto.BookData;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class BookDAO {
    private static BookDAO instance;

    private EntityManager entityManager;

    private BookDAO() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("AGH-MySQL");
        entityManager = factory.createEntityManager();
    }

    public static BookDAO getInstance() {
        if (instance == null) {
            instance = new BookDAO();
        }
        return instance;
    }

    public List<BookData> getItems() {
        Query query = entityManager.createQuery("FROM BookData", BookData.class);
        return query.getResultList();
    }

    public BookData getItem(Integer itemId) {
        Query query = entityManager.createQuery("FROM BookData WHERE BookData.id = :id");
        query.setParameter("id", itemId);
        return (BookData) query.getSingleResult();
    }

    public void addItem(BookData item) {
        try {
            entityManager.persist(item);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void updateItem(BookData item) {
        entityManager.merge(item);
        entityManager.getTransaction().commit();
    }

    public void deleteItem(BookData item) {
        entityManager.remove(entityManager.contains(item) ? item : entityManager.merge(item));
        entityManager.getTransaction().commit();
    }

    public void deleteItem(Integer itemId) {
        BookData item = getItem(itemId);
        deleteItem(item);
    }

}


