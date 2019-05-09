package soa.ejb.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Borrows")
@Access(AccessType.FIELD)
public class BorrowData extends AbstractDTO {

    public interface BorrowStatus {
        String BORROWED = "B";
        String RETURNED = "R";
    }

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private int id;

    @OneToOne
    private BookData bookId;

    @OneToOne
    private CustomerData cusId;

    @Column(name = "BorrowDate", nullable = false)
    private Date borrowDate;

    @Column(name = "ReturnDueDate", nullable = false)
    private Date returnDueDate;

    @Column(name = "ReturnedDate")
    private Date returnedDate;

    @Column(name = "Status")
    private String status;      // Possible values: B - borrowed, O - overdue, R - returned

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookData getBookId() {
        return bookId;
    }

    public void setBookId(BookData bookId) {
        this.bookId = bookId;
    }

    public CustomerData getCusId() {
        return cusId;
    }

    public void setCusId(CustomerData cusId) {
        this.cusId = cusId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDueDate() {
        return returnDueDate;
    }

    public void setReturnDueDate(Date returnDate) {
        this.returnDueDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }
}
