package soa.dao;

import org.hibernate.criterion.Order;
import soa.ejb.dto.BookData;
import soa.ejb.dto.BorrowData;
import soa.ejb.utils.SearchCriteria;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Type;
import java.util.ArrayList;
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

    public void returnBook(int bookId, int cusId) {
        BorrowData borrow = getBorrowByCustomerAndBook(cusId, bookId);
        borrow.setStatus(BorrowData.BorrowStatus.RETURNED);
        borrow.setReturnedDate(new Date());
        updateItem(borrow);
    }

    public boolean isBookBorrowed(int bookId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BorrowData> query = criteriaBuilder.createQuery(BorrowData.class);
        Root<BorrowData> b = query.from(BorrowData.class);
        query.select(b).where(criteriaBuilder.and(criteriaBuilder.equal(b.get("bookId"), bookId), criteriaBuilder.equal(b.get("status"), BorrowData.BorrowStatus.BORROWED)));
        TypedQuery<BorrowData> typedQuery = entityManager.createQuery(query);
        try {
            BorrowData item = typedQuery.getSingleResult();
            return item != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    public BorrowData getBorrowByCustomerAndBook(int customerId, int bookId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BorrowData> query = criteriaBuilder.createQuery(BorrowData.class);
        Root<BorrowData> b = query.from(BorrowData.class);
        query.select(b).where(criteriaBuilder.and(
                criteriaBuilder.and(
                        criteriaBuilder.equal(b.get("bookId"), bookId),
                        criteriaBuilder.equal(b.get("cusId"), customerId)
                ),
                criteriaBuilder.equal(b.get("status"), BorrowData.BorrowStatus.BORROWED)
        ));
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
        return typedQuery.getSingleResult().getReturnDueDate();
    }

    @Override
    protected void addBookConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<BorrowData> entity, List<Predicate> constraints) {
        if (searchCriteria.getBook().getId() != 0) {
            constraints.add(criteriaBuilder.equal(entity.get("bookId").get("id"), searchCriteria.getBook().getId()));
        }
        if (searchCriteria.getBook().getTitle() != null && !searchCriteria.getBook().getTitle().isEmpty()) {
            constraints.add(criteriaBuilder.equal(entity.get("bookId").get("title"), searchCriteria.getBook().getTitle()));
        }
        if (searchCriteria.getBook().getIsbn() != null && !searchCriteria.getBook().getIsbn().isEmpty()) {
            constraints.add(criteriaBuilder.equal(entity.get("bookId").get("isbn"), searchCriteria.getBook().getIsbn()));
        }
        if (searchCriteria.getBook().getYear() != null && !searchCriteria.getBook().getYear().isEmpty()) {
            constraints.add(criteriaBuilder.equal(entity.get("bookId").get("year"), searchCriteria.getBook().getYear()));
        }
    }

    @Override
    protected void addAuthorConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<BorrowData> entity, List<Predicate> constraints) {
        if (searchCriteria.getAuthor().getId() != 0) {
            constraints.add(criteriaBuilder.equal(entity.get("bookId").get("author").get("id"), searchCriteria.getAuthor().getId()));
        }
        if (searchCriteria.getAuthor().getName() != null && !searchCriteria.getAuthor().getName().isEmpty()) {
            constraints.add(criteriaBuilder.equal(entity.get("bookId").get("author").get("name"), searchCriteria.getAuthor().getName()));
        }
        if (searchCriteria.getAuthor().getSurname() != null && !searchCriteria.getAuthor().getSurname().isEmpty()) {
            constraints.add(criteriaBuilder.equal(entity.get("bookId").get("author").get("surname"), searchCriteria.getAuthor().getSurname()));
        }
    }

    @Override
    protected void addCustomerConstrains(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<BorrowData> entity, List<Predicate> constraints) {
        if (searchCriteria.getCustomer().getId() != 0) {
            constraints.add(criteriaBuilder.equal(entity.get("cusId").get("id"), searchCriteria.getCustomer().getId()));
        }
        if (searchCriteria.getCustomer().getName() != null && !searchCriteria.getCustomer().getName().isEmpty()) {
            constraints.add(criteriaBuilder.equal(entity.get("cusId").get("name"), searchCriteria.getCustomer().getName()));
        }
        if (searchCriteria.getCustomer().getSurname() != null && !searchCriteria.getCustomer().getSurname().isEmpty()) {
            constraints.add(criteriaBuilder.equal(entity.get("cusId").get("surname"), searchCriteria.getCustomer().getSurname()));
        }
    }

    @Override
    protected void addBorrowConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<BorrowData> entity, List<Predicate> constraints) {
        if (searchCriteria.getBorrow().getId() != 0) {
            constraints.add(criteriaBuilder.equal(entity.get("id"), searchCriteria.getBorrow().getId()));
        }
        if (searchCriteria.getBorrow().getStatus() != null && !searchCriteria.getBorrow().getStatus().isEmpty()) {
            constraints.add(criteriaBuilder.equal(entity.get("status"), searchCriteria.getBorrow().getStatus()));
        }
        if (searchCriteria.getBorrow().getBorrowDate() != null) {
            constraints.add(criteriaBuilder.equal(entity.get("borrowDate"), searchCriteria.getBorrow().getBorrowDate()));
        }
        if (searchCriteria.getBorrow().getReturnDueDate() != null) {
            constraints.add(criteriaBuilder.equal(entity.get("returnDueDate"), searchCriteria.getBorrow().getReturnDueDate()));
        }
        if (searchCriteria.getBorrow().getReturnedDate() != null) {
            constraints.add(criteriaBuilder.equal(entity.get("returnedDate"), searchCriteria.getBorrow().getReturnedDate()));
        }
    }

    public Long getBorrowsNumberForBook(BookData bookData) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<BorrowData> table = query.from(BorrowData.class);
        query.select(criteriaBuilder.count(table));
        query.where(criteriaBuilder.equal(table.get("bookId").get("id"), bookData.getId()));
        TypedQuery typedQuery = entityManager.createQuery(query);
        return (Long) typedQuery.getSingleResult();
    }
}
