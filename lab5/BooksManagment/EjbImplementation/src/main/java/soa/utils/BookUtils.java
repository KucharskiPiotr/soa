package soa.utils;

import java.util.*;

public class BookUtils {
    public interface BookStatus {
        String BORROWED = "BORROWED";
        String AVAILABLE = "AVAILABLE";
    }

    private Set<String> bookTypes;
    private Set<String> currencies;

    private static BookUtils instance;

    private BookUtils() {
        bookTypes = new HashSet<>();
        currencies = new HashSet<>();
    }

    public static BookUtils getInstance() {
        if (instance == null) {
            instance = new BookUtils();
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
