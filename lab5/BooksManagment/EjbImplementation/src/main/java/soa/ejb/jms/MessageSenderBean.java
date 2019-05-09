package soa.ejb.jms;

import soa.ejb.dto.BookData;
import soa.ejb.interfaces.local.jms.MessageSenderLocal;
import soa.ejb.utils.OperationStatus;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.*;

@Local(MessageSenderLocal.class)
@Singleton
public class MessageSenderBean implements MessageSenderLocal {

    @Inject
    private JMSContext context;

    @Resource(lookup = "java:jboss/exported/jms/topic/SOA_Test")
    private Topic topic;

    @Resource(lookup = "java:jboss/exported/jms/queue/SOA_test")
    private Queue queue;

    public void sendBookReturnInfo(BookData book) {
        JMSProducer producer = context.createProducer();
        producer.send(topic, "Book '" + book.getTitle() + "' by " + book.getAuthor() + " is now available");
    }
}
