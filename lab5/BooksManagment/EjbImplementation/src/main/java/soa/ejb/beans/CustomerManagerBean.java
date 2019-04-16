package soa.ejb.beans;

import soa.dao.BorrowDAO;
import soa.dao.CustomerDAO;
import soa.ejb.dto.BookData;
import soa.ejb.dto.BorrowData;
import soa.ejb.dto.CustomerData;
import soa.ejb.exceptions.BookAlreadyBorrowed;
import soa.ejb.exceptions.BookOverdued;
import soa.ejb.interfaces.local.BookManagerLocal;
import soa.ejb.interfaces.remote.CustomerManagerRemote;
import soa.utils.SystemParameters;

import javax.ejb.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(CustomerManagerRemote.class)
public class CustomerManagerBean implements CustomerManagerRemote {

    @EJB
    BookManagerLocal bookManager;

    @Override
    public List<BorrowData> getAllBorrowedBooks(int customerId) {
        return BorrowDAO.getInstance().getAllCustomerBorrows(customerId);
    }

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
    public List<BorrowData> getCurrentlyBorrowedBooks(int customerId) {
        return BorrowDAO.getInstance().getCustomerBorrowedBooks(customerId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<BorrowData> getCustomerBorrowHistory(int customerId) {
        return BorrowDAO.getInstance().getAllCustomerBorrows(customerId)
                .stream()
                .filter(bo -> BorrowData.BorrowStatus.RETURNED.equals(bo.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void borrowBook(int customerId, int bookId) throws BookAlreadyBorrowed {
        if (BorrowDAO.getInstance().isBookBorrowed(bookId)) {
            throw new BookAlreadyBorrowed();
        }
        CustomerData customer = getCustomer(customerId);
        BookData book = bookManager.getBook(bookId);
        BorrowData borrow = prepareNewBorrow(customer, book);
        BorrowDAO.getInstance().addItem(borrow);
    }

    private BorrowData prepareNewBorrow(CustomerData customer, BookData book) {
        BorrowData borrow = new BorrowData();
        borrow.setBookId(book);
        borrow.setCusId(customer);
        borrow.setBorrowDate(new Date());
        borrow.setStatus(BorrowData.BorrowStatus.BORROWED);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, SystemParameters.BORROW_MONTH_TIME);
        borrow.setReturnDueDate(c.getTime());
        return borrow;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void returnBook(int customerId, int bookId) throws BookOverdued {
        BorrowData borrowToBeEnded = BorrowDAO.getInstance().getBorrowByCustomerAndBook(customerId, bookId);
        if (new Date().after(borrowToBeEnded.getReturnDueDate())) {
            throw new BookOverdued();
        }
//        BorrowDAO.getInstance().deleteItem(borrowToBeEnded);
        BorrowDAO.getInstance().returnBook(bookId, customerId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addCustomer(CustomerData customer) {
        CustomerDAO.getInstance().addItem(customer);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeCustomer(CustomerData customer) {
        CustomerDAO.getInstance().deleteItem(customer);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeCustomer(int customerId) {
        CustomerDAO.getInstance().deleteItem(customerId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void modifyCustomer(CustomerData customer) {
        CustomerDAO.getInstance().updateItem(customer);
    }
}
