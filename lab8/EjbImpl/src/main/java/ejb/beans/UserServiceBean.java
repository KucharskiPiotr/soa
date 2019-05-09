package ejb.beans;

import ejb.dao.UserDAO;
import ejb.dto.UserData;
import ejb.interfaces.UserService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Remote(UserService.class)
public class UserServiceBean implements UserService {
    @Override
    public void addUser(UserData user) {
        UserDAO.getInstance().addItem(user);
    }

    @Override
    public UserData getUser(Integer userId) {
        return UserDAO.getInstance().getItem(userId);
    }

    @Override
    public List<UserData> getUsers() {
        return UserDAO.getInstance().getItems();
    }

    @Override
    public void deleteUser(UserData user) {
        UserDAO.getInstance().deleteItem(user);
    }

    @Override
    public void modifyUser(UserData user) {

    }
}
