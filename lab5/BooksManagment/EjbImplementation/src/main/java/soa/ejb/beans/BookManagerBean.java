package soa.ejb.beans;

import soa.dao.BookDAO;
import soa.ejb.dto.BookData;
import soa.ejb.interfaces.remote.BookManagerRemote;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Remote(BookManagerRemote.class)
public class BookManagerBean implements BookManagerRemote {

    @Override
    public List<BookData> getBooks() {
        return BookDAO.getInstance().getItems();
    }

    @Override
    public void addBook(BookData newBook) {
        BookDAO.getInstance().addItem(newBook);
    }

    @Override
    public void removeBook(BookData book) {
        BookDAO.getInstance().deleteItem(book);
    }

    @Override
    public void modifyBook(BookData book) {
        BookDAO.getInstance().updateItem(book);
    }
}
