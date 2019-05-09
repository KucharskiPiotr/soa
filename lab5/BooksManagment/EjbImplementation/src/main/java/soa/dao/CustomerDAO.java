package soa.dao;

import soa.ejb.dto.CustomerData;
import soa.ejb.utils.SearchCriteria;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class CustomerDAO extends AbstractDAO<CustomerData> {
    private static CustomerDAO instance;

    private CustomerDAO() {
        super(CustomerData.class);
    }

    public synchronized static CustomerDAO getInstance() {
        if (instance == null) {
            instance = new CustomerDAO();
        }
        return instance;
    }

    public CustomerData loginCustomer(String username, String password) {
        TypedQuery<CustomerData> query = entityManager.createQuery("SELECT data FROM CustomerData data WHERE data.login = :login AND data.password = :passwd", CustomerData.class);
        query.setParameter("login", username);
        query.setParameter("passwd", password);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    protected void addBookConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<CustomerData> entity, List<Predicate> constraints) {

    }

    @Override
    protected void addBorrowConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<CustomerData> entity, List<Predicate> constraints) {

    }

    @Override
    protected void addCustomerConstrains(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<CustomerData> entity, List<Predicate> constraints) {
        if (searchCriteria.getCustomer().getId() != 0) {
            constraints.add(criteriaBuilder.equal(entity.get("id"), searchCriteria.getCustomer().getId()));
        }
        if (searchCriteria.getCustomer().getName() != null && !searchCriteria.getCustomer().getName().isEmpty()) {
            constraints.add(criteriaBuilder.equal(entity.get("name"), searchCriteria.getCustomer().getName()));
        }
        if (searchCriteria.getCustomer().getSurname() != null && !searchCriteria.getCustomer().getSurname().isEmpty()) {
            constraints.add(criteriaBuilder.equal(entity.get("surname"), searchCriteria.getCustomer().getSurname()));
        }
    }

    @Override
    protected void addAuthorConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<CustomerData> entity, List<Predicate> constraints) {
    }

    public List<CustomerData> listCustomersSubscribedToBook(Integer bookId) {
        TypedQuery<CustomerData> query = entityManager.createQuery("SELECT data.customer FROM NotificationData data WHERE data.book.id = :bookId", CustomerData.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }
}

