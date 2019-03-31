package soa.ejb.dto;

import java.io.Serializable;

public class CustomerData implements Serializable {
    private Integer id;
    private String name;
    private String surname;
    private Integer balance;

    public CustomerData(Integer id, String name, String surname, Integer balance) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
