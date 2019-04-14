package soa.ejb.interfaces;

import soa.ejb.dto.BorrowData;
import soa.ejb.dto.CustomerData;

import java.util.List;

public interface CustomerManager {
    List<CustomerData> getCustomers();
    CustomerData getCustomer(int customerId);
    List<BorrowData> getBorrowedBooks(int customerId);
    void borrowBook(int bookId);
    void returnBook(int bookId);
    void addCustomer(CustomerData customer);
    void removeCustomer(CustomerData customer);
    void removeCustomer(int customerId);
    void modifyCustomer(CustomerData customer);
}
