package controller.proxy;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import controller.DatabaseController;
import controller.MainController;

import model.User;

public class RemoteServerControllerImpl extends UnicastRemoteObject implements RemoteServerController {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3441443474490088855L;
    User user;
    ROLE role;
    
    public RemoteServerControllerImpl() throws RemoteException {
        DatabaseController.getConnection();
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
	public RemoteServerController login(String user, String pass)
			throws RemoteException {
		return MainController.getInstance().getProxy(user, pass);
	}
	

	@Override
	public RemoteServerController logout() throws RemoteException {
		return MainController.getInstance().getProxy("","");
	}

	@Override
	public void sendMessage(String message) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getUserList() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
