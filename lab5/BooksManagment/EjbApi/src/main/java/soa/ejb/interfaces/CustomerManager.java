package soa.ejb.interfaces;

import soa.ejb.dto.BorrowData;
import soa.ejb.dto.CustomerData;
import soa.ejb.exceptions.BookAlreadyBorrowed;
import soa.ejb.exceptions.BookOverdued;

import java.util.List;

public interface CustomerManager {
    List<CustomerData> getCustomers();
    CustomerData getCustomer(int customerId);
    List<BorrowData> getCurrentlyBorrowedBooks(int customerId);
    List<BorrowData> getCustomerBorrowHistory(int customerId);
    List<BorrowData> getAllBorrowedBooks(int customerId);
    void borrowBook(int customerId, int bookId) throws BookAlreadyBorrowed;
    void returnBook(int customerId, int bookId) throws BookOverdued;
    void addCustomer(CustomerData customer);
    void removeCustomer(CustomerData customer);
    void removeCustomer(int customerId);
    void modifyCustomer(CustomerData customer);

}
