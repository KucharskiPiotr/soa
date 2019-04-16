package soa.dao;

import soa.ejb.dto.AuthorData;
import soa.ejb.utils.SearchCriteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class AuthorDAO extends AbstractDAO<AuthorData> {
    private static AuthorDAO instance;

    private AuthorDAO() {
        super(AuthorData.class);
    }

    public synchronized static AuthorDAO getInstance() {
        if (instance == null) {
            instance = new AuthorDAO();
        }
        return instance;
    }

    @Override
    protected void addBookConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<AuthorData> entity, List<Predicate> constraints) {
    }

    @Override
    protected void addBorrowConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<AuthorData> entity, List<Predicate> constraints) {
    }

    @Override
    protected void addCustomerConstrains(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<AuthorData> entity, List<Predicate> constraints) {
    }

    @Override
    protected void addAuthorConstraints(SearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<AuthorData> entity, List<Predicate> constraints) {
        if (searchCriteria.getAuthor().getId() != 0) {
            constraints.add(criteriaBuilder.equal(entity.get("id"), searchCriteria.getAuthor().getId()));
        }
        if (searchCriteria.getAuthor().getName() != null && !searchCriteria.getAuthor().getName().isEmpty()) {
            constraints.add(criteriaBuilder.equal(entity.get("name"), searchCriteria.getAuthor().getName()));
        }
        if (searchCriteria.getAuthor().getSurname() != null && !searchCriteria.getAuthor().getSurname().isEmpty()) {
            constraints.add(criteriaBuilder.equal(entity.get("surname"), searchCriteria.getAuthor().getSurname()));
        }
    }
}
