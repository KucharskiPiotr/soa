package soa.web;

import soa.ejb.dto.BookData;
import soa.ejb.interfaces.remote.BookManagerRemote;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named("BooksController")
@SessionScoped
public class BooksController implements Serializable {

    @EJB(lookup = "java:global/EjbImplementation-1.0/BookManagerBean!soa.ejb.interfaces.remote.BookManagerRemote")
    private BookManagerRemote bookManager;

    private List<BookData> books;

    private List<BookData> filteredBooks;

    private Boolean isPolishCurrency = false;

    public BooksController() {
        books = new ArrayList<>();
//        try {
//            books = BooksDAO.getInstance().loadBooksFromJson("D:\\Uczelnia\\SOA\\lab3\\Books\\books.json");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }

    public List<BookData> getItems() {
        return bookManager.getBooks();
    }

    public List<BookData> getFilteredItems() {
        return filteredBooks;
    }

    public void setFilteredItems(List<BookData> filteredItems) {
        this.filteredBooks = filteredItems;
    }

    public Boolean getPolishCurrency() {
        return isPolishCurrency;
    }

    public void setPolishCurrency(Boolean polishCurrency) {
        isPolishCurrency = polishCurrency;
    }

    public void changeCurrencyToDisplayInAllBooks() {
//        books.forEach(b -> b.getPrice().setShouldDisplayPolishPrice(isPolishCurrency));
    }

//    public BookUtils getBookUtils() {
//        return BookUtils.getInstance();
//    }

    public boolean filterByPrice(Object value, Object filter, Locale locale) {
        if (filter != null && filter.toString().matches("([0-9]|\\.)*-([0-9]|\\.)*")) {
            String filterText = filter.toString();
            Double fromPrice = Double.parseDouble(filterText.split("-")[0]);
            Double toPrice = Double.parseDouble(filterText.split("-")[1]);

            Double price = 0.0;
//            if (((BookData) value).getPrice().getShouldDisplayPolishPrice()) {
//                price = ((BookData) value).getPrice().getPolishPrice();
//            }
//            else {
//                price = ((BookData) value).getPrice().getPrice();
//            }

            return (price > fromPrice && price < toPrice);
        }
        return true;
    }

    public List<BookData> getBooks() {
        return bookManager.getBooks();
    }
}
