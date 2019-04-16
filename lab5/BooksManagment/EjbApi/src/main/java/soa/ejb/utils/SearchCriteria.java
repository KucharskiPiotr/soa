package soa.ejb.utils;

import soa.ejb.dto.AuthorData;
import soa.ejb.dto.BookData;
import soa.ejb.dto.BorrowData;
import soa.ejb.dto.CustomerData;

import java.io.Serializable;

public class SearchCriteria implements Serializable {
    private CustomerData customer;
    private BorrowData borrow;
    private BookData book;
    private AuthorData author;

    public enum GroupBy {
        BORROWS_QUANTITY("Book popularity"),
        AUTHOR_POPULARITY("Author popularity"),
        THE_GREATEST_READER("Customer activity")
        ;
        String description;

        GroupBy(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public enum OrderBy {
        ASCENDING("Ascending"),
        DESCENDING("Descending")
        ;

        String type;

        OrderBy(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    private GroupBy groupBy;
    private OrderBy orderBy;

    public SearchCriteria() {
        customer = new CustomerData();
        borrow = new BorrowData();
        book = new BookData();
        author = new AuthorData();
    }

    public GroupBy getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(GroupBy groupBy) {
        this.groupBy = groupBy;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
    }

    public CustomerData getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerData customer) {
        this.customer = customer;
    }

    public BorrowData getBorrow() {
        return borrow;
    }

    public void setBorrow(BorrowData borrow) {
        this.borrow = borrow;
    }

    public BookData getBook() {
        return book;
    }

    public void setBook(BookData book) {
        this.book = book;
    }

    public AuthorData getAuthor() {
        return author;
    }

    public void setAuthor(AuthorData author) {
        this.author = author;
    }
}
