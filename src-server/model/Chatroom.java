package model;

import java.util.List;

public class Chatroom {
	
	private String name;
	private List<User> users;
	
	public String getName() {
		return name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void addUser(User u) {
		if (!users.contains(u)) {
			users.add(u);
		}
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Chatroom(String name) {
		this.name = name;
	}

}
