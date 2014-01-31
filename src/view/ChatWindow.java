package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.DefaultListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JList;

import java.awt.Font;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;

import model.Chatroom;
import controller.proxy.RemoteServerController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8834486440559609837L;
	private static ChatWindow window;
	private RemoteServerController stub;
	private HashMap<String, ChatTab> chatrooms = new HashMap<String, ChatTab>();
	private JPanel contentPane;
	private DefaultListModel<String> serverChatrooms;
	private JTabbedPane tabbedPane;

	/**
	 * Singleton
	 */
	public static ChatWindow getInstance() {
		if(window == null) {
			return window = new ChatWindow();
		} else return window;
	}

	/**
	 * Create the frame.
	 */
	private ChatWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 649, 28);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("Fichier");
		mnFile.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnFile);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cr\u00E9er une chatroom");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new NewChatroomDialog();
			}
		});
		mnFile.add(mntmNewMenuItem);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnFile.add(mntmQuit);
		
		serverChatrooms = new DefaultListModel<String>();
		final JList<String> list = new JList<String>(serverChatrooms);
		list.setBounds(486, 64, 153, 363);
		contentPane.add(list);
		list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                  joinChatroom(list.getSelectedValue().toString());
            }
        });
		
		JLabel lblChatrooms = new JLabel("Chatrooms disponibles :");
		lblChatrooms.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChatrooms.setBounds(486, 39, 153, 28);
		contentPane.add(lblChatrooms);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 33, 468, 394);
		contentPane.add(tabbedPane);
		
		

		this.setVisible(true);
	}
	
	public void joinChatroom(String name) {
		if(chatrooms.get(name) == null) {
			try {
				Chatroom chatroom = stub.joinChatroom(name);
				
				ChatTab panel = new ChatTab(chatroom, stub);
				tabbedPane.addTab(name, null, panel, null);
				
				chatrooms.put(chatroom.getName(), panel);
				panel.addMessage("System", "Vous avez rejoint cette chatroom.");
				
				stub.sendMessage(chatroom, "coucou c moi");
				
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
	}

	public void setChatrooms(List<String> chatrooms) {
		for(String c : chatrooms) {
			serverChatrooms.addElement(c);
		}
	}

	public void setStub(RemoteServerController stub) {
		this.stub = stub;
	}

	public void setMessages(Chatroom chatroom) {
		chatrooms.get(chatroom.getName()).setMessages(chatroom);
	}
	
	public void setUsers(Chatroom chatroom) {
		chatrooms.get(chatroom.getName()).setUsers(chatroom.getUsers());
	}
}
