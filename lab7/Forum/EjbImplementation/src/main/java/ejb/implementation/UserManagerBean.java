package ejb.implementation;

import dao.UserDAO;
import ejb.dto.UserData;
import ejb.exceptions.InvalidLoginCredentialsException;
import ejb.interfaces.local.UserManagerLocal;
import ejb.interfaces.remote.UserManagerRemote;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Local(UserManagerLocal.class)
@Remote(UserManagerRemote.class)
public class UserManagerBean implements UserManagerLocal, UserManagerRemote {
    public UserData getUser(Integer userId) {
        return null;
    }

    public UserData loginUser(String username, String password) throws InvalidLoginCredentialsException {
        UserData user = UserDAO.getInstance().getUserByLoginCredentials(username, password);
        if (user == null) {
            throw new InvalidLoginCredentialsException();
        }
        return user;
    }

    public void createUser(String username, String password) {
        UserData user = new UserData();
        user.setUsername(username);
        user.setPassword(password);
        UserDAO.getInstance().addItem(user);
    }
}
