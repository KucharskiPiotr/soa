package soa.web;

import soa.ejb.dto.BookData;
import soa.ejb.interfaces.remote.BookManagerRemote;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("ModifyBook")
@SessionScoped
public class ModifyBookController implements Serializable {

    @EJB(lookup = "java:global/EjbImplementation-1.0/BookManagerBean!soa.ejb.interfaces.remote.BookManagerRemote")
    private BookManagerRemote bookManager;

    private BookData book;

    public BookData getBook() {
        return book;
    }

    public void setBook(BookData book) {
        this.book = book;
    }

    public void modify() {
        bookManager.modifyBook(book);
    }
}
