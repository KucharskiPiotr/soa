package web.controllers;

import ejb.dto.NotificationData;
import ejb.interfaces.remote.SubscribtionManagerRemote;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("Subscription")
@SessionScoped
public class SubscriptionController implements Serializable {

    @EJB(lookup = "java:global/EjbImplementation-1.0/SubscribtionManagerBean!ejb.interfaces.remote.SubscribtionManagerRemote")
    private SubscribtionManagerRemote subscribtionManager;

    public boolean isUserSubscribedToTopic(Integer userId, Integer topicId) {
        return subscribtionManager.isUserSubscribed(userId, topicId);
    }

    public List<NotificationData> listNotifications(Integer userId) {
        return subscribtionManager.getNotifications(userId);
    }

    public void unsubscribeTopic(Integer userId, Integer topicId) {
        subscribtionManager.unsubscribeTopic(userId, topicId);
    }

    public void subscribeTopic(Integer userId, Integer topicId) {
        subscribtionManager.subscribeTopic(userId, topicId);
    }
}
