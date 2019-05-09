package ejb.dao;

import ejb.dto.UserData;

public class UserDAO extends AbstractDAO<UserData> {
    private UserDAO() {
        super(UserData.class);
    }

    private static UserDAO instance;

    public static UserDAO getInstance() {
        if (instance == null) {
            synchronized (UserDAO.class) {
                if (instance == null) {
                    instance = new UserDAO();
                }
            }
        }
        return instance;
    }
}
