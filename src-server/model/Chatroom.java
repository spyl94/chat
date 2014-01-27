package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Chatroom implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7239467677875607155L;
	private String name;
	private List<User> users = new LinkedList<User>();
	private List<Message> messages = new LinkedList<Message>();
	
	public String getName() {
		return name;
	}

	public List<User> getUsers() {
		return users;
	}
	
	public List<Message> getMessages() {
		return messages;
	}

	public void addUser(User u) {
		if (!users.contains(u)) {
			users.add(u);
		}
	}
	
	public void addMessage(Message m) {
		messages.add(m);
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Chatroom(String name) {
		this.name = name;
	}

}
