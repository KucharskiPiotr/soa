package web.controllers;

import ejb.dto.TopicData;
import ejb.dto.UserData;
import ejb.exceptions.InvalidLoginCredentialsException;
import ejb.interfaces.remote.TopicManagerRemote;
import ejb.interfaces.remote.UserManagerRemote;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("GlobalController")
@SessionScoped
public class GlobalController implements Serializable {

    @EJB(lookup = "java:global/EjbImplementation-1.0/UserManagerBean!ejb.interfaces.remote.UserManagerRemote")
    UserManagerRemote userManager;

    @EJB(lookup = "java:global/EjbImplementation-1.0/TopicManagerBean!ejb.interfaces.remote.TopicManagerRemote")
    TopicManagerRemote topicManager;

    public GlobalController() {
    }

    public void sendMockComment() throws InvalidLoginCredentialsException {
        UserData user = userManager.loginUser("TEST", "TEST");
        List<TopicData> topics = topicManager.getTopics(null);
        topicManager.comment(user.getId(), topics.get(0).getId(), "TEST");
    }
}
