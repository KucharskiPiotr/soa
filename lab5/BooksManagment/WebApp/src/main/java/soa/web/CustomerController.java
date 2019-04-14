package soa.web;

import soa.ejb.dto.BookData;
import soa.ejb.dto.BorrowData;
import soa.ejb.dto.CustomerData;
import soa.ejb.interfaces.remote.BookManagerRemote;
import soa.ejb.interfaces.remote.CustomerManagerRemote;
import soa.web.helper.BorrowedBooksHelper;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("CustomerController")
@SessionScoped
public class CustomerController implements Serializable {
    // TODO: Not used (by now)
    private String customerPassword;

    private CustomerData customer;
    private BorrowedBooksHelper borrowedBooksHelper;

    @EJB(lookup = "java:global/EjbImplementation-1.0/CustomerManagerBean!soa.ejb.interfaces.remote.CustomerManagerRemote")
    CustomerManagerRemote customerManager;

    @EJB(lookup = "java:global/EjbImplementation-1.0/BookManagerBean!soa.ejb.interfaces.remote.BookManagerRemote")
    BookManagerRemote bookManager;

    public CustomerController() {
        customer = new CustomerData();
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public CustomerData getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerData customer) {
        this.customer = customer;
    }

    public List<CustomerData> listCustomers() {
        return customerManager.getCustomers();
    }

    public String printCustomerData(CustomerData customer) {
        return customer.getId() + ". " + customer.getName() + " " + customer.getSurname();
    }

    public void signUp() {
        customerManager.addCustomer(customer);
        customer = new CustomerData();
    }

    public void signIn() {
        customer = customerManager.getCustomers().stream()
                .filter(c -> c.getName().equals(customer.getName()) && c.getSurname().equals(customer.getSurname()))
                .findFirst().orElse(new CustomerData());
    }

    public List<BorrowData> getCurrentlyBorrowedBooks() {
        return customerManager.getCurrentlyBorrowedBooks(customer.getId());
    }
}
