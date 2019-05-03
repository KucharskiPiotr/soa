package ejb.implementation;

import dao.NotificationDAO;
import dao.SubscribtionDAO;
import dao.TopicDAO;
import dao.UserDAO;
import ejb.dto.NotificationData;
import ejb.dto.SubscribtionData;
import ejb.dto.TopicData;
import ejb.dto.UserData;
import ejb.dto.jms.NewCommentMessage;
import ejb.interfaces.local.SubscribtionManagerLocal;
import ejb.interfaces.remote.SubscribtionManagerRemote;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;
import java.util.List;

@Local(SubscribtionManagerLocal.class)
@Remote(SubscribtionManagerRemote.class)
@Stateful
public class SubscribtionManagerBean implements SubscribtionManagerLocal, SubscribtionManagerRemote {

    private static final String TITLE_PLACEHOLDER = "{topicTitle}";
    private static final String NEW_COMMENT_MSG = "There is new comment in " + TITLE_PLACEHOLDER + ", go and check updates!";

    @Resource(mappedName = "java:jboss/exported/jms/topic/SOA_Test")
    private Topic topic;

    @Inject
    private JMSContext context;

    public void subscribeTopic(Integer userId, Integer topicId) {
        SubscribtionData subscribtion = new SubscribtionData();
        UserData user = UserDAO.getInstance().getItem(userId);
        TopicData topic = TopicDAO.getInstance().getItem(topicId);
        subscribtion.setTopic(topic);
        subscribtion.setUser(user);
        subscribtion.setStatus(SubscribtionData.SubscribtionStatus.ACTIVE);
        SubscribtionDAO.getInstance().addItem(subscribtion);
    }

    public List<NotificationData> getNotifications(Integer userId) {
        return NotificationDAO.getInstance().getUserNotifications(userId);
    }

    public List<TopicData> getSubscribedTopics(Integer userId) {
        return SubscribtionDAO.getInstance().getUserSubscribedTopics(userId);
    }

    public void unsubscribeTopic(Integer userId, final Integer topicId) {
        SubscribtionData subscribtion = SubscribtionDAO.getInstance().getSubscribtion(userId, topicId);
        subscribtion.setStatus(SubscribtionData.SubscribtionStatus.INACTIVE);
        SubscribtionDAO.getInstance().updateItem(subscribtion);
    }

    @Override
    public void publishCommentMessage(TopicData topicData) {
        NewCommentMessage commentMessage = new NewCommentMessage();
        commentMessage.setTopicId(topicData.getId());
        commentMessage.setMessage(NEW_COMMENT_MSG.replace(TITLE_PLACEHOLDER, topicData.getTitle()));
        try {
            context.createProducer().send(topic, commentMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<UserData> getUsersSubscribedToTopic(Integer topicId) {
        return SubscribtionDAO.getInstance().getUsersForSubscribtion(topicId);
    }

    @Override
    public void addNotification(NotificationData notification) {
        NotificationDAO.getInstance().addItem(notification);
    }
}
