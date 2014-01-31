package controller.proxy;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.text.BadLocationException;

import model.Chatroom;
import model.Message;
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
		for (Message m : chat.getMessages()) {
			try {
				ChatWindow.getInstance().addMessage(m.getAuthor().getLogin(), m.getContent());
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
