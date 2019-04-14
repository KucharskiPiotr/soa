package soa.web.helper;

import soa.ejb.dto.BookData;

import java.util.Date;

public class BorrowedBooksHelper {
    private BookData book;
    private Date returnDate;

    public BorrowedBooksHelper(BookData book, Date returnDate) {
        this.book = book;
        this.returnDate = returnDate;
    }

    public BookData getBook() {
        return book;
    }

    public void setBook(BookData book) {
        this.book = book;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
