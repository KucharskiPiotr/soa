package soa.ejb.beans;

import soa.dao.BookDAO;
import soa.dao.BorrowDAO;
import soa.dao.CustomerDAO;
import soa.ejb.dto.BookData;
import soa.ejb.dto.BorrowData;
import soa.ejb.dto.CustomerData;
import soa.ejb.exceptions.BookAlreadyBorrowed;
import soa.ejb.exceptions.BookOverdued;
import soa.ejb.interfaces.local.BookManagerLocal;
import soa.ejb.interfaces.local.CustomerManagerLocal;
import soa.ejb.interfaces.local.jms.MessageSenderLocal;
import soa.ejb.interfaces.remote.CustomerManagerRemote;
import soa.utils.BookUtils;
import soa.utils.SystemParameters;

import javax.ejb.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(CustomerManagerRemote.class)
@Local(CustomerManagerLocal.class)
public class CustomerManagerBean implements CustomerManagerRemote, CustomerManagerLocal {

    @EJB
    BookManagerLocal bookManager;

    @EJB
    private MessageSenderLocal messageSender;

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
        BookData book = bookManager.getBook(bookId);
        if (BorrowDAO.getInstance().isBookBorrowed(bookId) && book.getStatus().equals(BookUtils.BookStatus.AVAILABLE)) {
            throw new BookAlreadyBorrowed();
        }
        CustomerData customer = getCustomer(customerId);
        BorrowData borrow = prepareNewBorrow(customer, book);
        BorrowDAO.getInstance().addItem(borrow);
        book.setStatus(BookUtils.BookStatus.BORROWED);
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
        BookData returnedBook = BookDAO.getInstance().getItem(bookId);
        returnedBook.setStatus(BookUtils.BookStatus.AVAILABLE);
//        BorrowDAO.getInstance().deleteItem(borrowToBeEnded);
        BorrowDAO.getInstance().returnBook(returnedBook.getId(), customerId);
        BookDAO.getInstance().updateItem(returnedBook);
        messageSender.sendBookReturnInfo(returnedBook);
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

    @Override
    public List<CustomerData> listCustomersSubscribedToBook(Integer bookId) {
        return CustomerDAO.getInstance().listCustomersSubscribedToBook(bookId);
    }

    @Override
    public CustomerData login(String username, String password) {
        return CustomerDAO.getInstance().loginCustomer(username, password);
    }
}
