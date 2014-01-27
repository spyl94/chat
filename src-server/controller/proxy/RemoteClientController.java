package controller.proxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteClientController extends Remote {
   
	public void sendPublicMessage(String message) throws RemoteException;
	
}
