package soa.web.helper;

import soa.web.helper.OperationStatus;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSProducer;
import javax.jms.Queue;

@Named
@RequestScoped
public class ConfirmationSender {

    @Inject
    private JMSContext context;

    @Resource(lookup = "java:jboss/exported/jms/queue/SOA_test")
    private Queue queue;

    public void sendConfirmation(OperationStatus status, String additionalMessage) {
        JMSProducer producer = context.createProducer();

        if (status.equals(OperationStatus.SUCCESS)) {
            producer.send(queue, status.getMessage());
        }
        else if (status.equals(OperationStatus.FAIL)) {
            producer.send(queue, status.getMessage() + " due to " + additionalMessage);
        }
    }
}
