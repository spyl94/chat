package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.DefaultListModel;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.SwingConstants;

public class ChatWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8834486440559609837L;
	private static ChatWindow window;
	private JPanel contentPane;
	private JTextField textField;
	private DefaultListModel<String> chatrooms;
	private JTextPane textPane;

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
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textPane = new JTextPane();
		textPane.setBounds(10, 39, 466, 349);
		contentPane.add(textPane);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 624, 28);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnFile);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnFile.add(mntmQuit);
		
		chatrooms = new DefaultListModel<String>();
		JList<String> list = new JList<String>(chatrooms);
		list.setBounds(486, 64, 128, 324);
		contentPane.add(list);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setBounds(10, 399, 466, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Envoyer");
		btnNewButton.setBounds(486, 399, 128, 28);
		contentPane.add(btnNewButton);
		
		JLabel lblChatrooms = new JLabel("Chatrooms :");
		lblChatrooms.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChatrooms.setBounds(486, 39, 84, 14);
		contentPane.add(lblChatrooms);
		
		
		

		this.setVisible(true);
	}

	public void addMessage(String author, String content) throws BadLocationException {
		StyledDocument doc = (StyledDocument)textPane.getDocument();
		Style style = doc.addStyle("Style", null);
		
        StyleConstants.setBold(style, true);
         doc.insertString(doc.getLength(), "[" + author + "] ", style);
		
        StyleConstants.setBold(style, false);   
		doc.insertString(doc.getLength(), content + "\n", style);
		
		textPane.setCaretPosition(doc.getLength());
		
	}

	public void addChatroom(String name) {
		chatrooms.addElement(name);
		
	}
}
