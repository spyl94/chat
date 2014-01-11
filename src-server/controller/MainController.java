package controller;

import java.lang.reflect.Proxy;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;


import controller.proxy.InvocationHandlerAdmin;
import controller.proxy.InvocationHandlerAnonymous;
import controller.proxy.InvocationHandlerUser;
import controller.proxy.ROLE;
import controller.proxy.RemoteServerController;
import controller.proxy.RemoteServerControllerImpl;

import model.*;
import model.dao.DaoFactory;
import model.dao.UserDao;

/**
 * @author Aurel
 * @Singleton
 * 
 */
public class MainController {

	private static MainController controller;

	/**
	 * Returns the MainController.
	 * 
	 * @return the instance of MainController
	 */
	public static MainController getInstance() {
		if (controller == null)
			new MainController();
		return controller;
	}

	public static void main(String[] args) {
		MainController.getInstance().init(args);
	}

	public void init(String[] args) {
	}

	private RemoteServerControllerImpl remote;
	private RemoteServerController proxremote;
	Registry registry;
	private UserDao userdao;

	private RemoteServerController getProxyAnonymous(RemoteServerController remote) {
		return (RemoteServerController) Proxy.newProxyInstance(remote.getClass()
				.getClassLoader(), remote.getClass().getInterfaces(),
				new InvocationHandlerAnonymous(remote));
	}

	private RemoteServerController getProxyAdmin(RemoteServerController remote) {
		return (RemoteServerController) Proxy.newProxyInstance(remote.getClass()
				.getClassLoader(), remote.getClass().getInterfaces(),
				new InvocationHandlerAdmin(remote));
	}
	
	private RemoteServerController getProxyUser(RemoteServerController remote) {
		return (RemoteServerController) Proxy.newProxyInstance(remote.getClass()
				.getClassLoader(), remote.getClass().getInterfaces(),
				new InvocationHandlerUser(remote));
	}

	public RemoteServerController getProxy(String login, String pass) {
		User u = userdao.findByLoginAndPass(login, pass);
		if (u == null) return getProxyAnonymous(remote);
		try {
			remote = new RemoteServerControllerImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (u instanceof User) {
			try {
			    remote.setGranted(ROLE.USER);
			    remote.setUser(u);
				return getProxyUser(remote);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			try {
			    remote.setGranted(ROLE.ADMIN);
			    remote.setUser(u);
				return getProxyAdmin(remote);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return getProxyAnonymous(remote);
	}

	/**
	 * The only constructor, the private no-argument constructor, can only be
	 * called from this class within the getInstance method. It should be called
	 * exactly once, the first time getInstance is called.
	 */
	private MainController() {
		if (controller == null)
			controller = this;
		else
			throw new IllegalArgumentException(
					"Default constructor called more than once.");

		try {
			registry = java.rmi.registry.LocateRegistry.createRegistry(13000);
			remote = new RemoteServerControllerImpl();
			proxremote = getProxyAnonymous(remote);
			userdao = (UserDao) DaoFactory.getUserDao();
			registry.rebind("RemoteServerController", proxremote);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}