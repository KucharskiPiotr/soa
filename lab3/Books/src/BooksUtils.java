import java.util.*;

public class BooksUtils {
    private Set<String> bookTypes;
    private Set<String> currencies;

    private static BooksUtils instance;

    private BooksUtils() {
        bookTypes = new HashSet<>();
        currencies = new HashSet<>();
    }

    public static BooksUtils getInstance() {
        if (instance == null) {
            instance = new BooksUtils();
        }
        return instance;
    }

    public Set<String> getBookTypes() {
        return bookTypes;
    }

    public Set<String> getCurrencies() {
        return currencies;
    }

    public static Double convertPlnToUsd(Double pln) {
        return pln * 0.26;
    }

    public static Double convertUsdToPln(Double usd) {
        return usd / 0.26;
    }

    public static Double convertPlnToEur(Double pln) {
        return pln * 0.23;
    }

    public static Double convertEurToPln(Double eur) {
        return eur / 0.23;
    }

    public static Double convertEurToUsd(Double eur) {
        return eur / 0.88;
    }

    public static Double convertUsdToEur(Double usd) {
        return usd * 0.88;
    }
}
