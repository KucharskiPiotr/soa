package soa.dao;

import soa.ejb.dto.SubscriptionData;
import soa.ejb.utils.SearchCriteria;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class SubscriptionDAO extends AbstractDAO<SubscriptionData> {
    private static SubscriptionDAO instance;

    private SubscriptionDAO() {
        super(SubscriptionData.class);
    }

    public static SubscriptionDAO getInstance() {
        if (instance == null) {
            instance = new SubscriptionDAO();
        }
        return instance;
    }

    public SubscriptionData getSubscription(Integer userId, Integer bookId) {
        TypedQuery<SubscriptionData> query = entityManager.createQuery("SELECT data FROM SubscriptionData data WHERE data.book.id = :bookId AND data.customer.id = :cusId", SubscriptionData.class);
        query.setParameter("bookId", bookId);
        query.setParameter("cusId", userId);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    protected void addBookConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<SubscriptionData> entity, List<Predicate> constraints) { }

    @Override
    protected void addBorrowConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<SubscriptionData> entity, List<Predicate> constraints) { }

    @Override
    protected void addCustomerConstrains(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<SubscriptionData> entity, List<Predicate> constraints) { }

    @Override
    protected void addAuthorConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<SubscriptionData> entity, List<Predicate> constraints) { }
}
