package controller;

import java.util.LinkedList;
import java.util.List;

import model.Chatroom;
import model.Message;
import model.User;

/**
 * @author Aurel
 * @Singleton
 * 
 */
public class ChatroomController {
	
	private static ChatroomController controller;
	
	private List<Chatroom> rooms = new LinkedList<Chatroom>();
	private List<User> users = new LinkedList<User>();

	/**
	 * Returns the ChatroomController.
	 * 
	 * @return the instance of ChatroomController
	 */
	public static ChatroomController getInstance() {
		if (controller == null)
			new ChatroomController();
		return controller;
	}

	/**
	 * The only constructor, the private no-argument constructor, can only be
	 * called from this class within the getInstance method. It should be called
	 * exactly once, the first time getInstance is called.
	 */
	private ChatroomController() {
		if (controller == null)
			controller = this;
		else
			throw new IllegalArgumentException(
					"Default constructor called more than once.");
	}
	
	/**
	 * Join or create a Chatroom.
	 * 
	 * @return the Chatroom
	 */
	public Chatroom joinChatroom(User u, String name) {
		for (Chatroom c : rooms) {
			if (c.getName().equals(name)) {
				if(!users.contains(u)) {
					users.add(u);
				}
				c.addUser(u);
				return c;
			}
		}
		Chatroom c = new Chatroom(name);
		rooms.add(c);
		if(!users.contains(u)) {
			users.add(u);
		}
		c.addUser(u);
		return c;
	}
	
	public List<Chatroom> getRooms() {
		return rooms;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public Chatroom sendMessage(Chatroom chat, Message m) {
		for (Chatroom c : rooms) {
			if (c.getName().equals(chat.getName())) {
				c.addMessage(m);
				return c;
			}
		}
		System.out.println("chat introuvable");
		return null;
	}


}
