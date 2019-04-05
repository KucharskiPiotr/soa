package soa.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
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
        Query query = entityManager.createNativeQuery("SELECT * FROM Books", BookData.class);
        return query.getResultList();
    }

}


