package soa.web;

import soa.ejb.dto.BookData;
import soa.ejb.dto.BorrowData;
import soa.ejb.dto.CustomerData;
import soa.ejb.interfaces.CustomerManager;
import soa.ejb.interfaces.remote.CustomerManagerRemote;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named("LibrarianController")
@SessionScoped
public class LibrarianController implements Serializable {

    @EJB(lookup = "java:global/EjbImplementation-1.0/CustomerManagerBean!soa.ejb.interfaces.remote.CustomerManagerRemote")
    CustomerManagerRemote customerManager;

    private CustomerData selectedCustomer;

    public List<CustomerData> listCustomers() {
        return customerManager.getCustomers();
    }

    public CustomerData getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(CustomerData selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public List<BorrowData> getCustomerBorrowedBooks() {
        return customerManager.getCurrentlyBorrowedBooks(selectedCustomer.getId());
    }

    public List<BorrowData> getCustomerBorrowHistory() {
        return customerManager.getCustomerBorrowHistory(selectedCustomer.getId());
    }

    public Date getTodaysDate() {
        return new Date();
    }
}
