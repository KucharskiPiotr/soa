package ejb.interfaces;

import ejb.dto.NotificationData;
import ejb.dto.TopicData;

import java.util.List;

public interface SubscribtionManager {
    void subscribeTopic(Integer userId, Integer topicId);
    List<NotificationData> getNotifications(Integer userId);
    List<TopicData> getSubscribedTopics(Integer userId);
    void unsubscribeTopic(Integer userId, Integer topicId);
}
