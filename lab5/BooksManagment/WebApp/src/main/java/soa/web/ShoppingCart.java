package soa.web;


import soa.ejb.dto.BookData;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Named("ShoppingCart")
@SessionScoped
public class ShoppingCart implements Serializable {
    private List<BookData> items = new ArrayList<>();
    private boolean isSummary = false;

    public List<BookData> getItems() {
        return items;
    }

    public void setItems(List<BookData> items) {
        this.items = items;
    }

    public void addItem(BookData item) {
        items.add(item);
    }

    public void removeItem(BookData item) {
        items.remove(item);
    }

    public int getItemQuantity(BookData item) {
        return items.stream().filter(i -> i == item).collect(Collectors.toList()).size();
    }

    public boolean isSummary() {
        return isSummary;
    }

    public void setSummary(boolean summary) {
        isSummary = summary;
    }

    public void setSummaryTrue() {
        isSummary = true;
    }

    public void setSummaryFalse() {
        isSummary = false;
    }

    public Double getTotalAmountPln() {
//        AtomicReference<Double> total = new AtomicReference<>((double) 0);
//        items.forEach(i -> total.getAndSet(total.get() + i.getPrice().getPolishPrice()));
//        return total.get();
        return 0.0;
    }

    public Double getTotalEur() {
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        items.forEach(i -> {
//            if (i.getPrice().getCurrency().equals("EUR")) {
//                total.getAndSet(total.get() + i.getPrice().getPrice());
//            }
        });
        return total.get();
    }

    public Double getTotalUsd() {
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        items.forEach(i -> {
//            if (i.getPrice().getCurrency().equals("USD")) {
//                total.getAndSet(total.get() + i.getPrice().getPrice());
//            }
        });
        return total.get();
    }

    public Double getTotalPln() {
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        items.forEach(i -> {
//            if (i.getPrice().getCurrency().equals("PLN")) {
//                total.getAndSet(total.get() + i.getPrice().getPrice());
//            }
        });
        return total.get();
    }

    public String getTotalText(boolean isPln) {
        DecimalFormat formatter = new DecimalFormat("#.##");
        if (isPln) {
            return formatter.format(getTotalAmountPln()) + " PLN";
        }
        else {
            return formatter.format(getTotalPln()) + " PLN + "
                    + formatter.format(getTotalEur()) + " EUR + "
                    + formatter.format(getTotalUsd()) + " USD";
        }
    }

    public String getItemQuantity(Object x) {
        return "asd";
    }
}
