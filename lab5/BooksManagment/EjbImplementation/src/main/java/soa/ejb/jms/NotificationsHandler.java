package soa.ejb.jms;

import soa.dao.NotificationDAO;
import soa.ejb.dto.CustomerData;
import soa.ejb.dto.NotificationData;
import soa.ejb.interfaces.local.CustomerManagerLocal;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.Date;
import java.util.List;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/exported/jms/topic/SOA_Test"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class NotificationsHandler implements MessageListener {

    @EJB
    private CustomerManagerLocal customerManager;

    @Override
    public void onMessage(Message message) {
        try {
            BookReleasedMessage msg = message.getBody(BookReleasedMessage.class);
            List<CustomerData> customers = customerManager.listCustomersSubscribedToBook(msg.getBook().getId());
            customers.forEach(c -> {
                NotificationData notification = new NotificationData();
                notification.setMessage(msg.getMessage());
                notification.setDate(new Date());
                notification.setBook(msg.getBook());
                notification.setCustomer(c);
                NotificationDAO.getInstance().addItem(notification);
            });

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
