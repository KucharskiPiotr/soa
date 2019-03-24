import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.awt.print.Book;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

@ManagedBean(name = "BooksDAO")
@ApplicationScoped
public class BooksDAO {

    private List<BookData> books;

    private List<BookData> filteredBooks;

    private BooksUtils booksUtils;

    private Boolean isPolishCurrency = false;

    public BooksDAO() {
        booksUtils = new BooksUtils();
        books = new ArrayList<BookData>();
        JSONParser parser = new JSONParser();
        try {
            File booksFile = new File("D:\\Uczelnia\\SOA\\lab3\\Books\\books.json");
            JSONArray booksArray = (JSONArray) ((JSONObject) parser.parse(new FileReader(booksFile))).get("books");
            for (JSONObject book : (Iterable<JSONObject>) booksArray) {
                String author = (String) book.get("author");
                String title = (String) book.get("title");
                String type = (String) book.get("type");
                JSONObject price = (JSONObject) book.get("price");
                Double bookValue = Double.parseDouble((String) price.get("price"));
                String currency = (String) price.get("currency");
                Integer pages = Integer.parseInt((String) book.get("pages"));
                BookPrice bookPrice = new BookPrice(bookValue, currency);
                BookData newBook = new BookData(author, title, type, bookPrice, pages);
                books.add(newBook);
                booksUtils.getCurrencies().add(currency);
                booksUtils.getBookTypes().add(newBook.getType());
            }
        } catch (Exception e) {
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

    public BooksUtils getBooksUtils() {
        return booksUtils;
    }

    public void setBooksUtils(BooksUtils booksUtils) {
        this.booksUtils = booksUtils;
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
}
