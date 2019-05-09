package soa.dao;

import soa.ejb.dto.NotificationData;
import soa.ejb.utils.SearchCriteria;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class NotificationDAO extends AbstractDAO<NotificationData> {
    private static NotificationDAO instance;

    private NotificationDAO() {
        super(NotificationData.class);
    }

    public static NotificationDAO getInstance() {
        if (instance == null) {
            instance = new NotificationDAO();
        }
        return instance;
    }

    public List<NotificationData> getUserNotifications(Integer userId) {
        TypedQuery<NotificationData> query = entityManager.createQuery("SELECT data FROM NotificationData data WHERE data.customer.id = :userId ORDER BY data.date DESC", NotificationData.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    // Not used
    @Override
    protected void addBookConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<NotificationData> entity, List<Predicate> constraints) { }
    @Override
    protected void addBorrowConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<NotificationData> entity, List<Predicate> constraints) { }
    @Override
    protected void addCustomerConstrains(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<NotificationData> entity, List<Predicate> constraints) { }
    @Override
    protected void addAuthorConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<NotificationData> entity, List<Predicate> constraints) { }
}
