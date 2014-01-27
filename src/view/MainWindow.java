package view;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import controller.proxy.ROLE;
import controller.proxy.RemoteServerController;
import model.*;

public class MainWindow {
	private static MainWindow mainWindow;
	private RemoteServerController stub;
	private Registry registry;
	private Connexion connexionWindow;
	
	public static MainWindow getInstance() {
		if (mainWindow == null)
			new MainWindow();
		return mainWindow;
	}
	
	public RemoteServerController getStub(){
		return stub;
	}
	
	public void setStub(RemoteServerController newest){
		stub = newest;
	}
	

	public void switchPanel(){
		connexionWindow.dispose();
		try {
			if(stub.getGranted() == ROLE.USER){
				List<Chatroom> chatrooms = stub.getChatroomList();
				for (Chatroom c : chatrooms) {
					System.out.println(c.getName());
				}
				stub.joinChatroom("testlala");
				for (Chatroom c : stub.getChatroomList()) {
					System.out.println(c.getName());
				}
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
	
	private MainWindow() {
		if (mainWindow == null)
			mainWindow = this;
		else
			throw new IllegalArgumentException(
					"Default constructor called more than once.");
		
		 try {
	            registry = LocateRegistry.getRegistry("localhost", 13000);
	            stub = (RemoteServerController) registry.lookup("RemoteServerController");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
		 connexionWindow = new Connexion();
		 
	}
		
	public static void main(String[] args) {
		MainWindow.getInstance();	
	}
}
