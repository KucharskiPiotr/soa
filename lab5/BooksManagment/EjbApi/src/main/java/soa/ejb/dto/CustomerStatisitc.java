package soa.ejb.dto;

import java.io.Serializable;

public class CustomerStatisitc implements Serializable {
    private CustomerData customer;
    private Long booksBorrowed;

    public CustomerData getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerData customer) {
        this.customer = customer;
    }

    public Long getBooksBorrowed() {
        return booksBorrowed;
    }

    public void setBooksBorrowed(Long booksBorrowed) {
        this.booksBorrowed = booksBorrowed;
    }
}
