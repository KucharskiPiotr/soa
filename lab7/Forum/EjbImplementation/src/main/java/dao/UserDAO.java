package dao;

import ejb.dto.UserData;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UserDAO extends AbstractDAO<UserData> {

    private static UserDAO instance;

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    private UserDAO() {
        super(UserData.class);
    }

    // TODO: Make it more secure
    public UserData getUserByLoginCredentials(String username, String password) {
        TypedQuery query = entityManager.createQuery("SELECT data FROM UserData data WHERE data.username = :username AND data.password = :password", UserData.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            return (UserData) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
