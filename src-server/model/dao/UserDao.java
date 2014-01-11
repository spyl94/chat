package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import model.User;

public class UserDao extends Dao<User> {

    public UserDao(Connection conn) {
        super(conn);
    }

    @Override
    public boolean create(User obj) {

        PreparedStatement addUser;
        try {
            addUser = conn.prepareStatement("INSERT INTO users (login,pass,sid,tid) VALUES(?,?,?,?)");
            addUser.setString(1, obj.getLogin());
            addUser.setString(2, obj.getPass());
            return addUser.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(User obj) {
        try {
            return !this.conn.createStatement().execute("DELETE FROM users WHERE login = " + obj.getLogin());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(User obj) {
        return false;
    }

    @Override
    public User find(int id) {
        return null;
    }

    public User findByLoginAndPass(String login, String pass) {
        User u = null;
        try {
            ResultSet rs = this.conn.createStatement().executeQuery(
                    "SELECT * FROM users WHERE login ='" + login +"' AND pass ='"+ pass+"'");
            
            u = new User(rs.getString("login"), rs.getString("pass"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public Set<User> findAll() {
        return null;
    }

}
