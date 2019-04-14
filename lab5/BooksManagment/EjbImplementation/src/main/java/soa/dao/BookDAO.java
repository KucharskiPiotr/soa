package soa.dao;

import soa.ejb.dto.BookData;

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
}


