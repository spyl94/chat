package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.MainController;
import controller.proxy.ROLE;
import controller.proxy.RemoteServerController;

public class MainWindow  {
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
/*
		try {
			if(MainWindow.getInstance().getStub().getGranted() == ROLE.STUDENT){
				Student actual = new Student();
				Set<Student> students = MainWindow.getInstance().getStub().getStudents();
				Student test = (Student) MainWindow.getInstance().getStub().getUser();
				for(Student i : students){
					if(i.getId() == test.getId()){
						actual = i;
					}
				}

				if(actual.getMajor().getId() == 0){
					newStudentWindow = new PopNewStudent();
				}
				else{
					new ViewStudent();
				}
			}
			else if(MainWindow.getInstance().getStub().getGranted() == ROLE.TEACHER){
				new ViewTeacher();
			}
			else if(MainWindow.getInstance().getStub().getGranted() == ROLE.ADMIN){
				new ViewAdmin();
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		*/
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
