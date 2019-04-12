package soa.web;

import soa.ejb.dto.AuthorData;
import soa.ejb.dto.BookData;
import soa.ejb.interfaces.remote.BookManagerRemote;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("AddBook")
@RequestScoped
public class AddBookController {
    private BookData newBook;

    @EJB(lookup = "java:global/EjbImplementation-1.0/BookManagerBean!soa.ejb.interfaces.remote.BookManagerRemote")
    private BookManagerRemote bookManager;

    public AddBookController() {
        newBook = new BookData();
        newBook.setAuthor(new AuthorData());
    }

    public BookData getNewBook() {
        return newBook;
    }

    public void setNewBook(BookData newBook) {
        this.newBook = newBook;
    }

    public void addBook() {
        bookManager.addBook(newBook);
    }
}
