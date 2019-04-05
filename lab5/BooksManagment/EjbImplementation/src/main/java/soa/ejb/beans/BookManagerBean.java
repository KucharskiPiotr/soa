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
}
