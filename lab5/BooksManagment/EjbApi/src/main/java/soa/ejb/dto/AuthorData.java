package soa.ejb.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Authors")
@Access(AccessType.FIELD)
public class AuthorData extends AbstractDTO implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private int id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Surname")
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
