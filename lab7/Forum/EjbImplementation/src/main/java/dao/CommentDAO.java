package dao;

import ejb.dto.CommentData;

import javax.persistence.TypedQuery;
import java.util.List;

public class CommentDAO extends AbstractDAO<CommentData> {
    private static CommentDAO instance;

    public static CommentDAO getInstance() {
        if (instance == null) {
            instance = new CommentDAO();
        }
        return instance;
    }

    private CommentDAO() {
        super(CommentData.class);
    }

    public List<CommentData> getCommentsOnTopic(Integer topicId) {
        TypedQuery<CommentData> query = entityManager.createQuery("SELECT data FROM CommentData data WHERE data.topic.id = :topicId ORDER BY data.date ASC", CommentData.class);
        query.setParameter("topicId", topicId);
        return query.getResultList();
    }
}
