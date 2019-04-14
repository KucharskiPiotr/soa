package soa.dao;

import soa.ejb.dto.CustomerData;

public class CustomerDAO extends AbstractDAO<CustomerData> {
    private static CustomerDAO instance;

    private CustomerDAO() {
        super(CustomerData.class);
    }

    public synchronized static CustomerDAO getInstance() {
        if (instance == null) {
            instance = new CustomerDAO();
        }
        return instance;
    };
}

