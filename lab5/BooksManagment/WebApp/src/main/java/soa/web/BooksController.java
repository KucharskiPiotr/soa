package soa.web;

import soa.ejb.dto.BookData;
import soa.ejb.interfaces.remote.BookManagerRemote;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named("BooksController")
@RequestScoped
public class BooksController implements Serializable {

    @EJB(lookup = "java:global/EjbImplementation-1.0/BookManagerBean!soa.ejb.interfaces.remote.BookManagerRemote")
    private BookManagerRemote bookManager;

    private List<BookData> books;

    private List<BookData> filteredBooks;

    @PostConstruct
    public void init() {
        books = bookManager.getBooks();
    }

    public BooksController() {}

    public List<BookData> getFilteredItems() {
        return filteredBooks;
    }

    public void setFilteredItems(List<BookData> filteredItems) {
        this.filteredBooks = filteredItems;
    }

    public void changeCurrencyToDisplayInAllBooks() {
    }

    public List<BookData> getBooks() {
        return books;
    }

    public void deleteBook(BookData book) {
        bookManager.removeBook(book);
        init();
    }
}
