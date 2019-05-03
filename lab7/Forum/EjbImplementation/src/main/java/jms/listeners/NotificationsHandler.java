package jms.listeners;

import ejb.dto.NotificationData;
import ejb.dto.UserData;
import ejb.dto.jms.NewCommentMessage;
import ejb.interfaces.local.SubscribtionManagerLocal;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@MessageDriven(activationConfig = {
            @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/exported/jms/topic/SOA_Test"),
            @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
        })
public class NotificationsHandler implements MessageListener {

    @EJB
    private SubscribtionManagerLocal subscribtionManager;

    @Override
    public void onMessage(Message message) {
        try {
            Serializable msgObj = ((ObjectMessage) message).getObject();
            if (msgObj instanceof NewCommentMessage) {
                List<UserData> users = subscribtionManager.getUsersSubscribedToTopic(((NewCommentMessage) msgObj).getTopicId());
                users.forEach(u -> {
                    NotificationData notification = new NotificationData();
                    notification.setDate(new Date());
                    notification.setContent(((NewCommentMessage) msgObj).getMessage());
                    notification.setUser(u);
                    subscribtionManager.addNotification(notification);
                });
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
