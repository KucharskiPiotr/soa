package soa.ejb.dto;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Entity()
@Table(name = "Books")
@Access(AccessType.FIELD)
public class BookData extends AbstractDTO {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = AuthorData.class)
    private AuthorData author;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "ISBN", nullable = false)
    private String isbn;

    @Column(name = "Year")
    private String year;

    @Column(name = "Status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BookData() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public AuthorData getAuthor() {
        return author;
    }

    public void setAuthor(AuthorData author) {
        this.author = author;
    }
}

