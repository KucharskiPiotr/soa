package dao;

import ejb.dto.SubscribtionData;
import ejb.dto.TopicData;
import ejb.dto.UserData;

import javax.persistence.TypedQuery;
import java.util.List;

public class SubscribtionDAO extends AbstractDAO<SubscribtionData> {
    private static SubscribtionDAO instance;

    public static SubscribtionDAO getInstance() {
        if (instance == null) {
            instance = new SubscribtionDAO();
        }
        return instance;
    }

    private SubscribtionDAO() {
        super(SubscribtionData.class);
    }

    public List<TopicData> getUserSubscribedTopics(Integer userId) {
        TypedQuery<TopicData> query = entityManager.createQuery("SELECT data.topic FROM SubscribtionData data where data.user = :userId", TopicData.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public SubscribtionData getSubscribtion(Integer userId, Integer topicId) {
        TypedQuery<SubscribtionData> query = entityManager.createQuery("SELECT data FROM SubscribtionData data where data.user = :userId and data.topic = :topicId", SubscribtionData.class);
        query.setParameter("userId", userId);
        query.setParameter("topicId", topicId);
        return query.getSingleResult();
    }

    public List<UserData> getUsersForSubscribtion(Integer topicId) {
        TypedQuery<UserData> query = entityManager.createQuery("SELECT data.user FROM SubscribtionData data where data.topic.id = :topicId", UserData.class);
        query.setParameter("topicId", topicId);
        return query.getResultList();
    }

}
