package controller.proxy;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.Chatroom;
import view.*;

public class RemoteClientControllerImpl extends UnicastRemoteObject implements RemoteClientController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7756857616393092227L;

    
    public RemoteClientControllerImpl() throws RemoteException {
    
    }

	@Override
	public void sendPublicMessage(String message) throws RemoteException {
		System.out.println("message public recu par le client :" + message);
	}

	@Override
	public void updateChatroom(Chatroom chat) throws RemoteException {
		ChatWindow.getInstance().setMessages(chat);
	}

}
