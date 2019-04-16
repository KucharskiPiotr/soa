package soa.dao;

import soa.ejb.dto.CustomerData;
import soa.ejb.utils.SearchCriteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

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

    @Override
    protected void addBookConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<CustomerData> entity, List<Predicate> constraints) {

    }

    @Override
    protected void addBorrowConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<CustomerData> entity, List<Predicate> constraints) {

    }

    @Override
    protected void addCustomerConstrains(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<CustomerData> entity, List<Predicate> constraints) {
        if (searchCriteria.getCustomer().getId() != 0) {
            constraints.add(criteriaBuilder.equal(entity.get("id"), searchCriteria.getCustomer().getId()));
        }
        if (searchCriteria.getCustomer().getName() != null && !searchCriteria.getCustomer().getName().isEmpty()) {
            constraints.add(criteriaBuilder.equal(entity.get("name"), searchCriteria.getCustomer().getName()));
        }
        if (searchCriteria.getCustomer().getSurname() != null && !searchCriteria.getCustomer().getSurname().isEmpty()) {
            constraints.add(criteriaBuilder.equal(entity.get("surname"), searchCriteria.getCustomer().getSurname()));
        }
    }

    @Override
    protected void addAuthorConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<CustomerData> entity, List<Predicate> constraints) {
    }
}

