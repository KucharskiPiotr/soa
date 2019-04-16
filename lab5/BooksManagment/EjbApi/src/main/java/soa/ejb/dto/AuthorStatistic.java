package soa.ejb.dto;

import java.io.Serializable;

public class AuthorStatistic implements Serializable {
    private AuthorData author;
    private Long authorsBooksBorrowed;

    public AuthorData getAuthor() {
        return author;
    }

    public void setAuthor(AuthorData author) {
        this.author = author;
    }

    public Long getAuthorsBooksBorrowed() {
        return authorsBooksBorrowed;
    }

    public void setAuthorsBooksBorrowed(Long authorsBooksBorrowed) {
        this.authorsBooksBorrowed = authorsBooksBorrowed;
    }
}
