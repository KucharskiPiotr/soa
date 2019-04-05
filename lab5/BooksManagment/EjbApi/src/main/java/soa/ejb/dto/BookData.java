package soa.ejb.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "BookData")
@Table(name = "Books", schema = "kuchars2")
@Access(AccessType.FIELD)
public class BookData implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Surname", nullable = false)
    private String authorSurname;

    @Column(name = "Name")
    private String authorName;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "ISBN", nullable = false)
    private String isbn;

    @Column(name = "Year")
    private String year;

    @Column(name = "Price", nullable = false)
    private Double price;

    public BookData() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

