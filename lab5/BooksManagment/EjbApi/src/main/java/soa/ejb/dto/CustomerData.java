package soa.ejb.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Customers")
@Access(AccessType.FIELD)
public class CustomerData extends AbstractDTO implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "Id",  nullable = false)
    private int id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Surname", nullable = false)
    private String surname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
