package soa.web;

import soa.ejb.dto.*;
import soa.ejb.interfaces.remote.SearchManagerRemote;
import soa.ejb.utils.SearchCriteria;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Named("SearchController")
@SessionScoped
public class SearchController implements Serializable {

    private SearchCriteria searchCriteria;
    private List<CustomerData> customerResults;
    private List<BorrowData> borrowResults;
    private List<AuthorData> authorResults;
    private List<BookData> bookResults;
    private List<CustomerStatisitc> customerStatisitcs;
    private List<AuthorStatistic> authorStatistics;
    private List<BookStatistic> bookStatistics;

    @EJB(lookup = "java:global/EjbImplementation-1.0/SearchManagerBean!soa.ejb.interfaces.remote.SearchManagerRemote")
    private SearchManagerRemote searchManager;

    public SearchController() {
        searchCriteria = new SearchCriteria();
    }

    public List<String> listBorrowStatuses() {
        return Arrays.asList(BorrowData.BorrowStatus.BORROWED, BorrowData.BorrowStatus.RETURNED);
    }

    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public List<CustomerData> getCustomerResults() {
        return customerResults;
    }

    public void setCustomerResults(List<CustomerData> customerResults) {
        this.customerResults = customerResults;
    }

    public List<BorrowData> getBorrowResults() {
        return borrowResults;
    }

    public void setBorrowResults(List<BorrowData> borrowResults) {
        this.borrowResults = borrowResults;
    }

    public List<AuthorData> getAuthorResults() {
        return authorResults;
    }

    public void setAuthorResults(List<AuthorData> authorResults) {
        this.authorResults = authorResults;
    }

    public List<BookData> getBookResults() {
        return bookResults;
    }

    public void setBookResults(List<BookData> bookResults) {
        this.bookResults = bookResults;
    }

    public List<CustomerStatisitc> getCustomerStatisitcs() {
        return customerStatisitcs;
    }

    public void setCustomerStatisitcs(List<CustomerStatisitc> customerStatisitcs) {
        this.customerStatisitcs = customerStatisitcs;
    }

    public List<AuthorStatistic> getAuthorStatistics() {
        return authorStatistics;
    }

    public void setAuthorStatistics(List<AuthorStatistic> authorStatistics) {
        this.authorStatistics = authorStatistics;
    }

    public List<BookStatistic> getBookStatistics() {
        return bookStatistics;
    }

    public void setBookStatistics(List<BookStatistic> bookStatistics) {
        this.bookStatistics = bookStatistics;
    }

    public void searchResults() {
        bookResults = searchManager.searchBooks(searchCriteria);
        authorResults = searchManager.searchAuthors(searchCriteria);
        customerResults = searchManager.searchCustomers(searchCriteria);
        borrowResults = searchManager.searchBorrows(searchCriteria);
        customerStatisitcs = searchManager.calculateStatForCustomers(searchCriteria);
        authorStatistics = searchManager.calculateStatForAuthors(searchCriteria);
        bookStatistics = searchManager.calculateStatForBooks(searchCriteria);
    }

    public void nullAllResults() {
        bookResults = null;
        authorResults = null;
        customerResults = null;
        borrowResults = null;
        customerStatisitcs = null;
        authorStatistics = null;
        bookStatistics = null;
    }
}
