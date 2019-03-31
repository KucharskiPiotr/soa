package soa.ejb.beans;

import soa.dao.CustomerDAO;
import soa.ejb.dto.CustomerData;
import soa.ejb.interfaces.local.LocalCustomerManager;
import soa.ejb.interfaces.remote.RemoteCustomerManager;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Local(LocalCustomerManager.class)
@Remote(RemoteCustomerManager.class)
public class CustomerManagerBean implements RemoteCustomerManager, LocalCustomerManager {
    @Override
    public List<CustomerData> getAllCustomers() {
        return CustomerDAO.getInstance().getItems();
    }

    @Override
    public Integer getCustomerBalance(CustomerData customer) {
        return CustomerDAO.getInstance().getItem(customer.getId()).getBalance();
    }

    @Override
    public CustomerData getCustomer(Integer customerId) {
        return CustomerDAO.getInstance().getItem(customerId);
    }
}
