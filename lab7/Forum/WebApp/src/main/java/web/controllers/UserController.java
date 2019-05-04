package web.controllers;

import ejb.dto.UserData;
import ejb.exceptions.InvalidLoginCredentialsException;
import ejb.interfaces.remote.UserManagerRemote;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("User")
@SessionScoped
public class UserController implements Serializable {

    @EJB(lookup = "java:global/EjbImplementation-1.0/UserManagerBean!ejb.interfaces.remote.UserManagerRemote")
    private UserManagerRemote userManager;

    private UserData user;

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public void login() {
        try {
            user = userManager.loginUser(user.getUsername(), user.getPassword());
        } catch (InvalidLoginCredentialsException e) {
            user = null;
        }
    }

    public void prepareUserToLogin() {
        user = new UserData();
    }

    public void register() throws InvalidLoginCredentialsException {
        userManager.createUser(user.getUsername(), user.getPassword());
        user = userManager.loginUser(user.getUsername(), user.getPassword());
    }
}
