package soa.ejb.beans;

import soa.dao.AuthorDAO;
import soa.dao.BookDAO;
import soa.dao.BorrowDAO;
import soa.dao.CustomerDAO;
import soa.ejb.dto.*;
import soa.ejb.interfaces.remote.SearchManagerRemote;
import soa.ejb.utils.SearchCriteria;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.*;

@Stateless
@Remote(SearchManagerRemote.class)
public class SearchManagerBean implements SearchManagerRemote {
    @Override
    public List<CustomerData> searchCustomers(SearchCriteria searchCriteria) {
        return CustomerDAO.getInstance().searchItems(searchCriteria);
    }

    @Override
    public List<BorrowData> searchBorrows(SearchCriteria searchCriteria) {
        return BorrowDAO.getInstance().searchItems(searchCriteria);
    }

    @Override
    public List<BookData> searchBooks(SearchCriteria searchCriteria) {
        return BookDAO.getInstance().searchItems(searchCriteria);
    }

    @Override
    public List<AuthorData> searchAuthors(SearchCriteria searchCriteria) {
        return AuthorDAO.getInstance().searchItems(searchCriteria);
    }

    @Override
    public List<BookStatistic> calculateStatForBooks(SearchCriteria searchCriteria) {
        List<BorrowData> filteredBorrows = BorrowDAO.getInstance().searchItems(searchCriteria);
        List<BookStatistic> stats = new ArrayList<>();
        for (BorrowData borrow : filteredBorrows) {
            if (stats.stream().noneMatch(s -> s.getBook() == borrow.getBookId())) {
                BookStatistic stat = new BookStatistic();
                stat.setBook(borrow.getBookId());
                Long borrowsNumber = BorrowDAO.getInstance().getBorrowsNumberForBook(borrow.getBookId());
                stat.setBorrowedNumber(borrowsNumber);
                stats.add(stat);
            }
        }
        return stats;
    }

    @Override
    public List<CustomerStatisitc> calculateStatForCustomers(SearchCriteria searchCriteria) {
        List<BorrowData> filteredBorrows = BorrowDAO.getInstance().searchItems(searchCriteria);
        List<CustomerStatisitc> stats = new ArrayList<>();
        for (BorrowData borrow : filteredBorrows) {
            if (stats.stream().noneMatch(s -> s.getCustomer() == borrow.getCusId())) {
                CustomerStatisitc stat = new CustomerStatisitc();
                stat.setCustomer(borrow.getCusId());
                Long borrowsNumber = BorrowDAO.getInstance().getBorrowsNumberForBook(borrow.getBookId());
                stat.setBooksBorrowed(borrowsNumber);
                stats.add(stat);
            }
        }
        return stats;
    }

    @Override
    public List<AuthorStatistic> calculateStatForAuthors(SearchCriteria searchCriteria) {
        List<BorrowData> filteredBorrows = BorrowDAO.getInstance().searchItems(searchCriteria);
        List<AuthorStatistic> stats = new ArrayList<>();
        for (BorrowData borrow : filteredBorrows) {
            if (stats.stream().noneMatch(s -> s.getAuthor() == borrow.getBookId().getAuthor())) {
                AuthorStatistic stat = new AuthorStatistic();
                stat.setAuthor(borrow.getBookId().getAuthor());
                Long borrowsNumber = BorrowDAO.getInstance().getBorrowsNumberForBook(borrow.getBookId());
                stat.setAuthorsBooksBorrowed(borrowsNumber);
                stats.add(stat);
            }
        }
        return stats;
    }
}
