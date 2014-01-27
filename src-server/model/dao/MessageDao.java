package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import model.Message;

public class MessageDao extends Dao<Message> {

    public MessageDao(Connection conn) {
        super(conn);
    }

    @Override
    public boolean create(Message obj) {
/*
        PreparedStatement addUser;
        try {
            addUser = conn.prepareStatement("INSERT INTO users (login,pass) VALUES(?,?)");
            addUser.setString(1, obj.getLogin());
            addUser.setString(2, obj.getPass());
            return addUser.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        */
    	return false;
    }

    @Override
    public boolean delete(Message obj) {
    	/*
        try {
            return !this.conn.createStatement().execute("DELETE FROM users WHERE login = " + obj.getLogin());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        */
    	return false;
    }

    @Override
    public boolean update(Message obj) {
        return false;
    }

    @Override
    public Message find(int id) {
        return null;
    }

    @Override
    public Set<Message> findAll() {
        return null;
    }

}
