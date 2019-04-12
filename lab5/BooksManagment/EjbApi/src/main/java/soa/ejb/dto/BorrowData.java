package soa.ejb.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Borrows")
@Access(AccessType.FIELD)
public class BorrowData extends AbstractDTO implements Serializable {
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

    @Column(name = "ReturnDate", nullable = false)
    private Date returnDate;

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

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
