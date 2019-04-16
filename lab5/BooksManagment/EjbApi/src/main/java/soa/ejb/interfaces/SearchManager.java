package soa.ejb.interfaces;

import soa.ejb.dto.*;
import soa.ejb.utils.SearchCriteria;

import java.util.List;

public interface SearchManager {
    List<CustomerData> searchCustomers(SearchCriteria searchCriteria);
    List<BorrowData> searchBorrows(SearchCriteria searchCriteria);
    List<BookData> searchBooks(SearchCriteria searchCriteria);
    List<AuthorData> searchAuthors(SearchCriteria searchCriteria);
    List<BookStatistic> calculateStatForBooks(SearchCriteria searchCriteria);
    List<CustomerStatisitc> calculateStatForCustomers(SearchCriteria searchCriteria);
    List<AuthorStatistic> calculateStatForAuthors(SearchCriteria searchCriteria);
}
