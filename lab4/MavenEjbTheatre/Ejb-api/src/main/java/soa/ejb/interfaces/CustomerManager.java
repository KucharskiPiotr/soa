package soa.ejb.interfaces;

import soa.ejb.dto.CustomerData;

import java.util.List;

public interface CustomerManager {
    List<CustomerData> getAllCustomers();

    Integer getCustomerBalance(CustomerData customer);

    CustomerData getCustomer(Integer customerId);
}
