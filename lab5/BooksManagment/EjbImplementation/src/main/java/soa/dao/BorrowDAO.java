package soa.dao;

import soa.ejb.dto.BorrowData;

public class BorrowDAO extends AbstractDAO<BorrowData> {
    private static BorrowDAO instance;

    private BorrowDAO() {
        super(BorrowData.class);
    }

    public static BorrowDAO getInstance() {
        if (instance == null) {
            instance = new BorrowDAO();
        }
        return instance;
    }
}
