package soa.dao;

import soa.ejb.dto.BookData;
import soa.ejb.dto.BorrowData;

import javax.persistence.criteria.*;
import java.util.Arrays;
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
        CriteriaQuery<BookData> query = cb.createQuery(BookData.class);
        Root<BorrowData> b = query.from(BorrowData.class);
        Join<BorrowData, BookData> borrowJoin = b.join("bookId", JoinType.RIGHT);
        borrowJoin.on(cb.equal(borrowJoin.get("id"), b.get("bookId")));
        query.select(borrowJoin).where(
                cb.or(
                        cb.isNull(b.get("status")),
                        cb.and(
                                cb.notEqual(b.get("status"), "B"),
                                cb.notEqual(b.get("status"), "O")
                        )
                )
        );
        return entityManager.createQuery(query).getResultList();
    }
}


