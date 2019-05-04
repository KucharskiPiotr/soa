package ejb.interfaces.local;

import ejb.dto.NotificationData;
import ejb.dto.TopicData;
import ejb.dto.UserData;
import ejb.interfaces.SubscribtionManager;

import javax.ejb.Local;
import java.util.List;

@Local
public interface SubscribtionManagerLocal extends SubscribtionManager {
    void publishCommentMessage(TopicData topic, Integer userId);
    List<UserData> getUsersSubscribedToTopic(Integer topicId);
    void addNotification(NotificationData notification);
}
