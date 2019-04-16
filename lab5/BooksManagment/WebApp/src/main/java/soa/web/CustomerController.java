package soa.web;

import soa.ejb.dto.BookData;
import soa.ejb.dto.BorrowData;
import soa.ejb.dto.CustomerData;
import soa.ejb.exceptions.BookAlreadyBorrowed;
import soa.ejb.exceptions.BookOverdued;
import soa.ejb.interfaces.remote.BookManagerRemote;
import soa.ejb.interfaces.remote.CustomerManagerRemote;
import soa.web.exceptions.CustomerNotFound;
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
    private BookData selectedBook;
    private BorrowedBooksHelper borrowedBooksHelper;

    @EJB(lookup = "java:global/EjbImplementation-1.0/CustomerManagerBean!soa.ejb.interfaces.remote.CustomerManagerRemote")
    CustomerManagerRemote customerManager;

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

    public void signUp() {
        customerManager.addCustomer(customer);
        customer = new CustomerData();
    }

    public void signIn() throws CustomerNotFound {
        customer = customerManager.getCustomers().stream()
                .filter(c -> c.getName().equals(customer.getName()) && c.getSurname().equals(customer.getSurname()))
                .findFirst().orElse(null);
        if (customer == null) {
            customer = new CustomerData();
            throw new CustomerNotFound();
        }
    }

    public List<BorrowData> getCurrentlyBorrowedBooks() {
        return customerManager.getCurrentlyBorrowedBooks(customer.getId());
    }

    public BookData getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(BookData selectedBook) {
        this.selectedBook = selectedBook;
    }

    public void borrowSelectedBook() {
        try {
            customerManager.borrowBook(customer.getId(), selectedBook.getId());
            selectedBook = null;
        } catch (BookAlreadyBorrowed bookAlreadyBorrowed) {
            bookAlreadyBorrowed.printStackTrace();
        }
    }

    public void returnBook(BorrowData selectedBook) {
        if (selectedBook != null) {
            try {
                customerManager.returnBook(customer.getId(), selectedBook.getBookId().getId());
            } catch (BookOverdued bookOverdued) {
                bookOverdued.printStackTrace();
            }
        }
    }
}
