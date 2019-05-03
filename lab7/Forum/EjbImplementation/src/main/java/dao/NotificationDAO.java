package dao;

import ejb.dto.NotificationData;

import javax.management.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class NotificationDAO extends AbstractDAO<NotificationData> {
    private static NotificationDAO instance;

    private NotificationDAO() {
        super(NotificationData.class);
    }

    public static NotificationDAO getInstance() {
        if (instance == null) {
            instance = new NotificationDAO();
        }
        return instance;
    }

    public List<NotificationData> getUserNotifications(int userId) {
        TypedQuery query = entityManager.createQuery("SELECT n FROM NotificationData n WHERE n.user = :userId", NotificationData.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
