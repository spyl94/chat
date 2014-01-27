package controller.proxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.*;

public interface RemoteClientController extends Remote {
   
	public void sendPublicMessage(String message) throws RemoteException;
	public void updateChatroom(Chatroom chat) throws RemoteException;
	
}
