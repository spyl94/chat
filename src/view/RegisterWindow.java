package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Component;

import javax.swing.Box;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import controller.proxy.RemoteClientControllerImpl;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class RegisterWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2282154998217780396L;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField passwordField;
	private JPasswordField passwordField2;

	/**
	 * Create the frame.
	 */
	public RegisterWindow() {
		setTitle("Chat - Inscription");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblAfinDeVous = new JLabel("Pour vous inscrire, merci de renseigner vos identifiants :");
		contentPane.add(lblAfinDeVous);
		
		Box verticalBox = Box.createVerticalBox();
		contentPane.add(verticalBox);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_2);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);
		
		JLabel lblNomDutilisateur = new JLabel("Nom d'utilisateur :");
		horizontalBox_1.add(lblNomDutilisateur);
		
		Box verticalBox_1 = Box.createVerticalBox();
		horizontalBox_1.add(verticalBox_1);
		
		txtLogin = new JTextField();
		txtLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		horizontalBox_1.add(txtLogin);
		txtLogin.setColumns(25);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		horizontalBox_2.add(lblMotDePasse);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordField.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordField.setColumns(25);
		horizontalBox_2.add(passwordField);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_1);
		
		Box horizontalBox_3 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_3);
		
		JLabel label = new JLabel("Répetez :");
		horizontalBox_3.add(label);
		
		passwordField2 = new JPasswordField();
		horizontalBox_3.add(passwordField2);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_3);
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		
		JButton btnLogin = new JButton("Inscription");
		horizontalBox.add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent arg0){
				    try {
				    	String pass = new String(passwordField.getPassword());
				    	String pass2 = new String(passwordField2.getPassword());
				    	
				    	if(! pass.equals(pass2))
				    		JOptionPane.showMessageDialog(RegisterWindow.this, "Les deux mot de passe ne sont pas identiques !");
				    	
				    	else if(txtLogin.getText() == "" || pass == "" || pass2 == "")
				    		JOptionPane.showMessageDialog(RegisterWindow.this, "Au moins l'un des champs est vide !");
				    	
				    	else {

					    	MainWindow.getInstance().getStub().register(txtLogin.getText(), pass);
					    	MainWindow.getInstance().setStub(MainWindow.getInstance().getStub().login(txtLogin.getText(), pass));
					    	MainWindow.getInstance().getStub().setClientStub(new RemoteClientControllerImpl());
					    	MainWindow.getInstance().switchPanel();
				    	}
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				  }
		});
		
		this.setVisible(true);
	}

}
