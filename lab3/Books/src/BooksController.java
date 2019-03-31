import org.json.simple.parser.ParseException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.IOException;
import java.util.*;

@ManagedBean(name = "BooksController")
@ApplicationScoped
public class BooksController {

    private List<BookData> books;

    private List<BookData> filteredBooks;

    private Boolean isPolishCurrency = false;

    public BooksController() {
        books = new ArrayList<>();
        try {
            books = BooksDAO.getInstance().loadBooksFromJson("D:\\Uczelnia\\SOA\\lab3\\Books\\books.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public List<BookData> getItems() {
        return books;
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
        books.forEach(b -> b.getPrice().setShouldDisplayPolishPrice(isPolishCurrency));
    }

    public BooksUtils getBookUtils() {
        return BooksUtils.getInstance();
    }

    public boolean filterByPrice(Object value, Object filter, Locale locale) {
        if (filter != null && filter.toString().matches("([0-9]|\\.)*-([0-9]|\\.)*")) {
            String filterText = filter.toString();
            Double fromPrice = Double.parseDouble(filterText.split("-")[0]);
            Double toPrice = Double.parseDouble(filterText.split("-")[1]);

            Double price;
            if (((BookData) value).getPrice().getShouldDisplayPolishPrice()) {
                price = ((BookData) value).getPrice().getPolishPrice();
            }
            else {
                price = ((BookData) value).getPrice().getPrice();
            }

            return (price > fromPrice && price < toPrice);
        }
        return true;
    }
}
