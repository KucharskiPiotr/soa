package soa.ejb.dto;

import soa.ejb.interfaces.CustomerManager;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Subscriptions")
public class SubscriptionData extends AbstractDTO {
    @Column(name = "Id")
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private CustomerData customer;

    @OneToOne
    private BookData book;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerData getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerData customer) {
        this.customer = customer;
    }

    public BookData getBook() {
        return book;
    }

    public void setBook(BookData book) {
        this.book = book;
    }
}
