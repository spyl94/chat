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
	private AuthWindow authWindow;
	
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
		authWindow.dispose();
		String str = "";
		try {
			if(stub.getGranted() == ROLE.USER){
				List<Chatroom> chatrooms = stub.getChatroomList();
				for (Chatroom c : chatrooms) {
					System.out.println(c.getName());
				}
				Chatroom testlala = stub.joinChatroom("testlala");
				for (Chatroom c : stub.getChatroomList()) {
					str += c.getName();
				}
				stub.sendMessage(testlala, "coucou c moi");
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		new DialogBox("Liste des chatrooms", str);
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
	        
		 authWindow = new AuthWindow();
		 
	}
		
	public static void main(String[] args) {
		MainWindow.getInstance();	
	}
}
