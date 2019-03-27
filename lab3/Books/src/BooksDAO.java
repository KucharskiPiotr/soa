import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BooksDAO {
    private static BooksDAO instance = new BooksDAO();

    public static BooksDAO getInstance() {
        if (instance == null) {
            instance = new BooksDAO();
        }
        return instance;
    }

    public List<BookData> loadBooksFromJson(String jsonPath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        List<BookData> books = new ArrayList<>();
        File booksFile = new File(jsonPath);
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
            BooksUtils.getInstance().getCurrencies().add(currency);
            BooksUtils.getInstance().getBookTypes().add(newBook.getType());
        }
        return books;
    }
}
