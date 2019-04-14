package soa.dao;

import soa.ejb.dto.AuthorData;

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
}
