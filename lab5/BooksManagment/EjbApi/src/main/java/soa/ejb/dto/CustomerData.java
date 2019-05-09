package soa.ejb.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Customers")
@Access(AccessType.FIELD)
public class CustomerData extends AbstractDTO {
    @Id
    @GeneratedValue
    @Column(name = "Id",  nullable = false)
    private int id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Surname", nullable = false)
    private String surname;

    @Column(name = "Login", nullable = false, unique = true)
    private String login;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "NewBookNotif", nullable = false)
    private Boolean newBookNotification;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getNewBookNotification() {
        return newBookNotification;
    }

    public void setNewBookNotification(Boolean newBookNotification) {
        this.newBookNotification = newBookNotification;
    }

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
