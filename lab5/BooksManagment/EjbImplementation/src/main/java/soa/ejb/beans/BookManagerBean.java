package soa.ejb.beans;

import soa.dao.BookDAO;
import soa.ejb.dto.BookData;
import soa.ejb.interfaces.local.BookManagerLocal;
import soa.ejb.interfaces.remote.BookManagerRemote;

import javax.ejb.*;
import java.util.List;

@Stateless
@Remote(BookManagerRemote.class)
@Local(BookManagerLocal.class)
public class BookManagerBean implements BookManagerRemote, BookManagerLocal {

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<BookData> getBooks() {
        return BookDAO.getInstance().getItems();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addBook(BookData newBook) {
        BookDAO.getInstance().addItem(newBook);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeBook(BookData book) {
        BookDAO.getInstance().deleteItem(book);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void modifyBook(BookData book) {
        BookDAO.getInstance().updateItem(book);
    }

    @Override
    public BookData getBook(int bookId) {
        return BookDAO.getInstance().getItem(bookId);
    }

    @Override
    public List<BookData> getAvailableBooks() {
        return BookDAO.getInstance().getAvailableBooks();
    }
}
