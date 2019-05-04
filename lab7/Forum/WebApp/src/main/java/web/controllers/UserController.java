package web.controllers;

import ejb.dto.NotificationData;
import ejb.dto.UserData;
import ejb.exceptions.InvalidLoginCredentialsException;
import ejb.interfaces.remote.SubscribtionManagerRemote;
import ejb.interfaces.remote.UserManagerRemote;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.xml.registry.infomodel.User;
import java.io.Serializable;
import java.util.List;

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

    public void login() throws InvalidLoginCredentialsException {
        user = userManager.loginUser(user.getUsername(), user.getPassword());
    }

    public void prepareUserToLogin() {
        user = new UserData();
    }
}
