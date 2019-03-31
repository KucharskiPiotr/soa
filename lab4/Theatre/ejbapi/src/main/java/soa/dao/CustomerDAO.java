package soa.dao;

import soa.ejb.dto.CustomerData;
import soa.ejb.dto.EventData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerDAO {
    private static CustomerDAO instance;

    private CustomerDAO() {

    }

    public static CustomerDAO getInstance() {
        if (instance == null) {
            instance = new CustomerDAO();
        }
        return instance;
    }

    public List<CustomerData> getItems() {
        return MockCacheData.getInstance().getCustomers();
    }

    public CustomerData getItem(Integer id) {
        return MockCacheData.getInstance().getCustomers().stream().filter(c -> c.getId().equals(id)).findAny().orElse(null);
    }

    public void updateItem(CustomerData customer) {
        MockCacheData.getInstance().getCustomers()
                .stream()
                .filter(c -> c.getId().equals(customer.getId()))
                .findAny()
                .ifPresent(c -> {
                    c.setBalance(customer.getBalance());
                    c.setName(customer.getName());
                    c.setSurname(customer.getSurname());
                });
    }

}
