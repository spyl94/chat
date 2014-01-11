package controller;

import java.lang.reflect.Proxy;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import controller.proxy.InvocationHandlerAdmin;
import controller.proxy.InvocationHandlerAnonymous;
import controller.proxy.ROLE;
import controller.proxy.RemoteServerController;
import controller.proxy.RemoteServerControllerImpl;

import model.*;

/**
 * @author Aurel
 * @Singleton
 * 
 */
public class ChatController {

	private static ChatController controller;

	/**
	 * Returns the ChatController.
	 * 
	 * @return the instance of ChatController
	 */
	public static ChatController getInstance() {
		if (controller == null)
			new ChatController();
		return controller;
	}

	/**
	 * The only constructor, the private no-argument constructor, can only be
	 * called from this class within the getInstance method. It should be called
	 * exactly once, the first time getInstance is called.
	 */
	private ChatController() {
		if (controller == null)
			controller = this;
		else
			throw new IllegalArgumentException(
					"Default constructor called more than once.");
	}

}