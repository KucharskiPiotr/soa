package ejb.implementation;

import ejb.dto.TopicData;
import ejb.interfaces.local.SubscribtionManagerLocal;
import ejb.interfaces.remote.SubscribtionManagerRemote;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Local(SubscribtionManagerLocal.class)
@Remote(SubscribtionManagerRemote.class)
@Stateless
public class SubscribtionManagerBean implements SubscribtionManagerLocal, SubscribtionManagerRemote {
    public void subscribeTopic(Integer userId, Integer topicId) {

    }

    public void getNotifications(Integer userId) {

    }

    public List<TopicData> getSubscribedTopics(Integer userId) {
        return null;
    }

    public void unsubscribeTopic(Integer userId, Integer topicId) {

    }
}
