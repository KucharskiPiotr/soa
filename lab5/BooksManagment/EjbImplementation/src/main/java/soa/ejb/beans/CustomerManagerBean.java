package soa.ejb.beans;

import soa.dao.BorrowDAO;
import soa.dao.CustomerDAO;
import soa.ejb.dto.BorrowData;
import soa.ejb.dto.CustomerData;
import soa.ejb.interfaces.remote.CustomerManagerRemote;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
@Remote(CustomerManagerRemote.class)
public class CustomerManagerBean implements CustomerManagerRemote {
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<CustomerData> getCustomers() {
        return CustomerDAO.getInstance().getItems();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public CustomerData getCustomer(int customerId) {
        return CustomerDAO.getInstance().getItem(customerId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<BorrowData> getBorrowedBooks(int customerId) {
        // TODO: Use criteriaAPI
        return BorrowDAO.getInstance().getItems();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void borrowBook(int bookId) {

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void returnBook(int bookId) {

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addCustomer(CustomerData customer) {

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeCustomer(CustomerData customer) {

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeCustomer(int customerId) {

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void modifyCustomer(CustomerData customer) {

    }
}
