package soa.ejb.interfaces;


import soa.ejb.dto.BookData;

import java.util.List;

public interface BookManager {
    List<BookData> getBooks();

}
