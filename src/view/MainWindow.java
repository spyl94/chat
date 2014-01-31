package view;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import controller.proxy.RemoteServerController;

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
		ChatWindow chatWindow = ChatWindow.getInstance();
		chatWindow.setStub(stub);
		chatWindow.joinChatroom("Home");
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
