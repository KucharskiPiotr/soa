package soa.ejb.interfaces;


import soa.ejb.dto.BookData;

import java.util.List;

public interface BookManager {
    List<BookData> getBooks();
    BookData getBook(int bookId);
    List<BookData> getAvailableBooks();
    void addBook(BookData newBook);
    void removeBook(BookData book);
    void modifyBook(BookData book);
}
