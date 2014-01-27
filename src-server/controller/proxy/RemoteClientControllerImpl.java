package controller.proxy;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;


public class RemoteClientControllerImpl extends UnicastRemoteObject implements RemoteClientController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7756857616393092227L;

    
    public RemoteClientControllerImpl() throws RemoteException {
    
    }

	@Override
	public void sendPublicMessage(String message) throws RemoteException {
		System.out.println("message recu par le client :" + message);
		
	}

}
