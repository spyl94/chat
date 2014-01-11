package controller.proxy;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.*;

public interface RemoteServerController extends Remote {
   
    // Other
    public RemoteServerController login(String user, String pass) throws RemoteException;
    public RemoteServerController logout() throws RemoteException;
    public User getUser() throws RemoteException;
    public ROLE getGranted() throws RemoteException;
    
    public void joinChatroom(String name) throws RemoteException;
    public List<Chatroom> getChatroomList() throws RemoteException;
    public void sendMessage(String message) throws RemoteException;
    public List<User> getUserList() throws RemoteException;
}
