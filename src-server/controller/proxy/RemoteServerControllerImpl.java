package controller.proxy;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import controller.ChatroomController;
import controller.DatabaseController;
import controller.MainController;

import model.Chatroom;
import model.Message;
import model.User;
import model.dao.Dao;
import model.dao.DaoFactory;

public class RemoteServerControllerImpl extends UnicastRemoteObject implements RemoteServerController {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3441443474490088855L;
    User user;
    ROLE role;
    Dao<User> dao = (Dao<User>) DaoFactory.getUserDao();
    private ChatroomController chatroom;
    
    public RemoteServerControllerImpl() throws RemoteException {
        DatabaseController.getConnection();
        chatroom = ChatroomController.getInstance();
        user = null;
        role = ROLE.ANONYMOUS;
    }
    
    public void setUser(User u) {
        user = u;
    }
    
    @Override
    public User getUser() {
        return user;
    }
    
    public void setGranted(ROLE r) {
        role = r;
    }
    
    @Override
    public ROLE getGranted() {
        return role;
    }
    
    @Override
    public void setClientStub(RemoteClientController stub) throws RemoteException {
    	user.setStub(stub);
    }

	@Override
	public RemoteServerController login(String user, String pass)
			throws RemoteException {
		return MainController.getInstance().getProxy(user, pass);
	}
	

	@Override
	public RemoteServerController logout() throws RemoteException {
		return MainController.getInstance().getProxy("","");
	}

	@Override
	public Chatroom joinChatroom(String name) throws RemoteException {
		user.getStub().sendPublicMessage(user.getLogin() + " viens de rejoindre la chatroom " + name);
		return chatroom.joinChatroom(user, name);
	}

	@Override
	public List<Chatroom> getChatroomList() throws RemoteException {
		return chatroom.getRooms();
	}

	@Override
	public void sendMessage(Chatroom chat, String message)
			throws RemoteException {
		Chatroom c = chatroom.sendMessage(chat, new Message(user, message));
		if (c == null) return;
		for (User u : c.getUsers()) {
			if (u.getStub() != null) {
				u.getStub().updateChatroom(c);
			} else {
				System.out.println(u.getLogin() + " non connecté !");
			}
		}
	}

	@Override
	public boolean register(String user, String pass)
			throws RemoteException {
		return dao.create(new User(user,pass));
	}

}
