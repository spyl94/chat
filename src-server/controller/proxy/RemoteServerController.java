package controller.proxy;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.*;

public interface RemoteServerController extends Remote {
	
    // Chatroom methods
    public Chatroom joinChatroom(String name) throws RemoteException;
    public List<Chatroom> getChatroomList() throws RemoteException;
    public void sendMessage(Chatroom chat, String message) throws RemoteException;
   
    // Other
    public RemoteServerController login(String user, String pass) throws RemoteException;
    public boolean register(String user, String pass) throws RemoteException;
    public void setClientStub(RemoteClientController stub) throws RemoteException;
    public RemoteServerController logout() throws RemoteException;
    public User getUser() throws RemoteException;
    public ROLE getGranted() throws RemoteException;
}
