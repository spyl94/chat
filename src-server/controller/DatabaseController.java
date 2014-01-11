package controller;

import java.sql.*;

import model.Pair;
import model.User;
import model.dao.DaoFactory;
import model.dao.UserDao;

public class DatabaseController {
    private String url = "jdbc:sqlite:chat.db";
    private static Connection connect;

    public static final String USERS = "create table users (id integer, login string, pass string, PRIMARY KEY(id))";
        
    public static final String[] POPULATE_USERS_LOGIN = { "aurel", "adrien","pierre","jean","michel","admin"};
    public static final String[] POPULATE_USER_PASS = { "aurel", "adrien","pierre","jean","michel","admin" };

    private UserDao userdao = null;

    private DatabaseController() {
        try {
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection(url);
            userdao = (UserDao) DaoFactory.getUserDao();
            createDatabase();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (connect == null) {
            new DatabaseController();
        }
        return connect;
    }

    public void createDatabase() {
        try {
            Statement statement = connect.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate(USERS);

            populateDatabase();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateDatabase() {
        System.out.println("Populating Database ...");
        
        int i = 0;
        for (String s : POPULATE_USERS_LOGIN) {
            if (i <= 2) userdao.create(new User(s, POPULATE_USER_PASS[i],i+1,0));
            else if (i <= 4) userdao.create(new User(s, POPULATE_USER_PASS[i],0,i-2));
            else  userdao.create(new User(s, POPULATE_USER_PASS[i]));
            i++;
        }
        System.out.println("Database Populated");
    }
}
