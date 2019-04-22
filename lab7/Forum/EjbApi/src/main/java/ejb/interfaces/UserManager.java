package ejb.interfaces;

import ejb.dto.UserData;
import ejb.exceptions.InvalidLoginCredentialsException;

public interface UserManager {
    UserData getUser(Integer userId);
    UserData loginUser(String username, String password) throws InvalidLoginCredentialsException;
    void createUser(String username, String password);
}
