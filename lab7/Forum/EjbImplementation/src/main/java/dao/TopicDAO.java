package dao;

import ejb.dto.TopicData;

public class TopicDAO extends AbstractDAO<TopicData> {
    private static TopicDAO instance;

    public static TopicDAO getInstance() {
        if (instance == null) {
            instance = new TopicDAO();
        }
        return instance;
    }
    public TopicDAO() {
        super(TopicData.class);
    }
}
