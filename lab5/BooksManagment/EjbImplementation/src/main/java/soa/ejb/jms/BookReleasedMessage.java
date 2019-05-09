package soa.ejb.jms;

import soa.ejb.dto.BookData;

import java.io.Serializable;

public class BookReleasedMessage implements Serializable {
    private BookData book;
    private String message;

    public BookData getBook() {
        return book;
    }

    public void setBook(BookData book) {
        this.book = book;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
