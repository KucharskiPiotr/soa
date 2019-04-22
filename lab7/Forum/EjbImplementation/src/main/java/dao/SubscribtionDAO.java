package dao;

import ejb.dto.SubscribtionData;

public class SubscribtionDAO extends AbstractDAO<SubscribtionData> {
    private static SubscribtionDAO instance;

    public static SubscribtionDAO getInstance() {
        if (instance == null) {
            instance = new SubscribtionDAO();
        }
        return instance;
    }

    public SubscribtionDAO() {
        super(SubscribtionData.class);
    }
}
