package dao;

import ejb.dto.CommentData;

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
}
