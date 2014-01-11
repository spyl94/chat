package model.dao;

import java.sql.Connection;

import controller.DatabaseController;

public class DaoFactory {

    protected static final Connection conn = DatabaseController.getConnection();
    
    /**
     * @return DAO
     */
    public static Dao<?> getUserDao() {
        return new UserDao(conn);
    }

}
