package ejb.interfaces;

import ejb.dto.UserData;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface UserService {
    void addUser(UserData user);
    UserData getUser(Integer userId);
    List<UserData> getUsers();
    void deleteUser(UserData user);
    void modifyUser(UserData user);
}
