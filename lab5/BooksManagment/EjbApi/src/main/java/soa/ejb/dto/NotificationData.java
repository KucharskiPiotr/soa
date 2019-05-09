package soa.ejb.dto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Notifications")
public class NotificationData extends AbstractDTO {
    @Column(name = "Id")
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "Message")
    private String message;

    @OneToOne
    private CustomerData customer;

    @OneToOne
    private BookData book;

    @Column(name = "Date")
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
