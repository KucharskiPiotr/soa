package soa.dao;

import soa.ejb.dto.BorrowData;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BorrowDAO extends AbstractDAO<BorrowData> {
    private static BorrowDAO instance;

    private BorrowDAO() {
        super(BorrowData.class);
    }

    public synchronized static BorrowDAO getInstance() {
        if (instance == null) {
            instance = new BorrowDAO();
        }
        return instance;
    }

    public boolean isBookBorrowed(int bookId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BorrowData> query = criteriaBuilder.createQuery(BorrowData.class);
        Root<BorrowData> b = query.from(BorrowData.class);
        query.select(b).where(criteriaBuilder.equal(b.get("bookId"), bookId));
        TypedQuery<BorrowData> typedQuery = entityManager.createQuery(query);
        BorrowData item = typedQuery.getSingleResult();
        return item != null;
    }

    public BorrowData getBorrowByCustomerAndBook(int customerId, int bookId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BorrowData> query = criteriaBuilder.createQuery(BorrowData.class);
        Root<BorrowData> b = query.from(BorrowData.class);
        query.select(b).where(criteriaBuilder.equal(b.get("bookId"), bookId)).where(criteriaBuilder.equal(b.get("customerId"), customerId));
        TypedQuery<BorrowData> typedQuery = entityManager.createQuery(query);
        return typedQuery.getSingleResult();
    }

    public List<BorrowData> getCustomerBorrowedBooks(int customerId) {
        TypedQuery<BorrowData> typedQuery = prepareBorrowsForCustomerQuery(customerId);
        return typedQuery.getResultList().stream().filter(bo -> BorrowData.BorrowStatus.BORROWED.equals(bo.getStatus())).collect(Collectors.toList());
    }

    public List<BorrowData> getAllCustomerBorrows(int customerId) {
        TypedQuery<BorrowData> typedQuery = prepareBorrowsForCustomerQuery(customerId);
        return typedQuery.getResultList();
    }

    private TypedQuery<BorrowData> prepareBorrowsForCustomerQuery(int customerId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BorrowData> query = criteriaBuilder.createQuery(BorrowData.class);
        Root<BorrowData> b = query.from(BorrowData.class);
        query.select(b).where(criteriaBuilder.equal(b.get("cusId"), customerId));
        return entityManager.createQuery(query);
    }

    private Date getReturnDateForBook(int customerId, int bookId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BorrowData> query = criteriaBuilder.createQuery(BorrowData.class);
        Root<BorrowData> b = query.from(BorrowData.class);
        query.select(b).where(criteriaBuilder.equal(b.get("bookId"), bookId)).where(criteriaBuilder.equal(b.get("cusId"), customerId));
        TypedQuery<BorrowData> typedQuery = entityManager.createQuery(query);
        return typedQuery.getSingleResult().getReturnDate();
    }
}
