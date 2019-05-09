package soa.web;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.IOException;

@Named("Confirmation")
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/exported/jms/queue/SOA_test"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ConfirmationController implements MessageListener {

    @Inject
    private CustomerController testController;

    @Override
    public void onMessage(Message message) {
//        try {
//            testController.setAsd(message.getBody(String.class));
//            testController.setReady(true);
//            testController.test();
//            testController.setResultMessage(message.getBody(String.class));
//            testController.setReady(true);
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
    }
}
