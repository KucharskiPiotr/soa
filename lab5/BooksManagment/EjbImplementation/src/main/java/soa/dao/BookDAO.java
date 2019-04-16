package soa.dao;

import soa.ejb.dto.BookData;
import soa.ejb.dto.BorrowData;
import soa.ejb.utils.SearchCriteria;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class BookDAO extends AbstractDAO<BookData> {
    private static BookDAO instance;

    private BookDAO() {
        super(BookData.class);
    }

    public synchronized static BookDAO getInstance() {
        if (instance == null) {
            instance = new BookDAO();
        }
        return instance;
    }

    public List<BookData> getAvailableBooks() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookData> cq = cb.createQuery(BookData.class);
        Root<BookData> book = cq.from(BookData.class);
        Path<Integer> bookId = book.get("id");
        CriteriaQuery<BookData> booksSelect = cq.select(book);

        // Inner query - borrowed books
        Subquery<BookData> borrows = cq.subquery(BookData.class);
        Root<BorrowData> subRoot = borrows.from(BorrowData.class);
        borrows.select(subRoot.get("bookId"));
        borrows.where(cb.equal(subRoot.get("status"), "B"));

        booksSelect.where(cb.not(bookId.in(borrows)));

        TypedQuery<BookData> typedQuery = entityManager.createQuery(booksSelect);
        return typedQuery.getResultList();
    }

    @Override
    protected void addBookConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<BookData> entity, List<Predicate> constraints) {
        if (searchCriteria.getBook().getId() != 0) {
            constraints.add(criteriaBuilder.equal(entity.get("id"), searchCriteria.getBook().getId()));
        }
        if (searchCriteria.getBook().getTitle() != null && !searchCriteria.getBook().getTitle().isEmpty()) {
            constraints.add(criteriaBuilder.equal(entity.get("title"), searchCriteria.getBook().getTitle()));
        }
        if (searchCriteria.getBook().getIsbn() != null && !searchCriteria.getBook().getIsbn().isEmpty()) {
            constraints.add(criteriaBuilder.equal(entity.get("isbn"), searchCriteria.getBook().getIsbn()));
        }
        if (searchCriteria.getBook().getYear() != null && !searchCriteria.getBook().getYear().isEmpty()) {
            constraints.add(criteriaBuilder.equal(entity.get("year"), searchCriteria.getBook().getYear()));
        }
    }

    @Override
    protected void addBorrowConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<BookData> entity, List<Predicate> constraints) {
    }

    @Override
    protected void addCustomerConstrains(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<BookData> entity, List<Predicate> constraints) {
    }

    @Override
    protected void addAuthorConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<BookData> entity, List<Predicate> constraints) {
        if (searchCriteria.getAuthor().getId() != 0) {
            constraints.add(criteriaBuilder.equal(entity.get("author").get("id"), searchCriteria.getAuthor().getId()));
        }
        if (searchCriteria.getAuthor().getName() != null && !searchCriteria.getAuthor().getName().isEmpty()) {
            constraints.add(criteriaBuilder.equal(entity.get("author").get("name"), searchCriteria.getAuthor().getName()));
        }
        if (searchCriteria.getAuthor().getSurname() != null && !searchCriteria.getAuthor().getSurname().isEmpty()) {
            constraints.add(criteriaBuilder.equal(entity.get("author").get("surname"), searchCriteria.getAuthor().getSurname()));
        }
    }
}


