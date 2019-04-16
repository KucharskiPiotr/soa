package soa.ejb.dto;

import java.io.Serializable;

public class BookStatistic implements Serializable {
    private BookData book;
    private Long borrowedNumber;

    public BookData getBook() {
        return book;
    }

    public void setBook(BookData book) {
        this.book = book;
    }

    public Long getBorrowedNumber() {
        return borrowedNumber;
    }

    public void setBorrowedNumber(Long borrowedNumber) {
        this.borrowedNumber = borrowedNumber;
    }
}
