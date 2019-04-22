package ejb.interfaces;

import ejb.dto.TopicData;

import java.util.List;

public interface SubscribtionManager {
    void subscribeTopic(Integer userId, Integer topicId);
    void getNotifications(Integer userId);
    List<TopicData> getSubscribedTopics(Integer userId);
    void unsubscribeTopic(Integer userId, Integer topicId);
}
