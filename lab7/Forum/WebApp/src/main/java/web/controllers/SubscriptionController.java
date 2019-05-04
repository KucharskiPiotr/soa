package web.controllers;

import ejb.dto.NotificationData;
import ejb.interfaces.remote.SubscribtionManagerRemote;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("Subscription")
@SessionScoped
public class SubscriptionController implements Serializable {

    @EJB(lookup = "java:global/EjbImplementation-1.0/SubscribtionManagerBean!ejb.interfaces.remote.SubscribtionManagerRemote")
    private SubscribtionManagerRemote subscribtionManager;

    List<NotificationData> notifications;

    public List<NotificationData> getNotifications(Integer userId) {
        if (notifications == null && userId != null) {
            notifications = subscribtionManager.getNotifications(userId);
        }
        return notifications;
    }

    public void setNotifications(List<NotificationData> notifications) {
        this.notifications = notifications;
    }

    public boolean isUserSubscribedToTopic(Integer userId, Integer topicId) {
        return subscribtionManager.isUserSubscribed(userId, topicId);
    }

    public List<NotificationData> listNotifications(Integer userId) {
        if (userId != null)
            return subscribtionManager.getNotifications(userId);
        return new ArrayList<>();
    }

    public void unsubscribeTopic(Integer userId, Integer topicId) {
        subscribtionManager.unsubscribeTopic(userId, topicId);
    }

    public void subscribeTopic(Integer userId, Integer topicId) {
        subscribtionManager.subscribeTopic(userId, topicId);
    }
}
