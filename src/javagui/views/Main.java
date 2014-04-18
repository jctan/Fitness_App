
package javagui.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.event.*;
import java.awt.*;

public class Main extends JFrame {
	
	private JButton btnLogin;
	private JPanel contentPane; //save all elements of interface
	private JLabel lblID;
	private JLabel lblPassword;;
	private JTextField IDField;
	private JPasswordField PasswordField;
	
	private ImageIcon image;
	private JLabel pictureLabel;
	
	//my variables created 
	private String userID; 
	private String userPassword;
	private boolean validated = false;
	private String userRole = null;
	
	
	private Login loginFunction; 
	private DataManager dm;
	private DataBean userDetails; 
	private JPanel cp; //
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	//main 
	public Main() {
		mainFrame();
		loginEvent();
		
	}
	
	private void mainFrame(){
		setTitle("Fitness App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/javagui/resources/workout-icon.png")));
		
		//close JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(500, 400, 500, 400);
		
		//open up new JPanel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(129, 68, 260, 197);
		panel.setBorder(new TitledBorder(null, "Login To Fitness App", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblID = new JLabel("User ID");
		lblID.setBounds(20, 21, 73, 16);
		panel.add(lblID);
		
		IDField = new JTextField();
		IDField.setBounds(20, 43, 134, 28);
		panel.add(IDField);
		IDField.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(20, 83, 61, 16);
		panel.add(lblPassword);
		
		btnLogin = new JButton("Login");

		btnLogin.setBounds(20, 151, 117, 29);
		panel.add(btnLogin);
		
		PasswordField = new JPasswordField();
		PasswordField.setBounds(20, 111, 134, 28);
		panel.add(PasswordField);
		
	}
	

	
	private void loginEvent(){
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				userID = IDField.getText();
				userPassword = PasswordField.getText();
				
				loginFunction = new Login();
				validated = loginFunction.loginValidate(userID, userPassword); //validate login 
				
				if(validated == true){
				userDetails = new DataBean(); //declare databean for Userdetail 
				dm = new DataManager(); //declare new object of datamanager 
				userDetails = dm.getUser(userID); //getuser id into data bean.
				userRole = loginFunction.userRole(userID, userRole);
				
				//next interface that extends JPanel
				cp = new ControlPanel(userDetails); //dataBean
				contentPane.removeAll();
				contentPane.setLayout(new BorderLayout());
				contentPane.add(cp, BorderLayout.CENTER);
				contentPane.revalidate();
				contentPane.repaint();
				pack();
				
			}
			else{
				JOptionPane.showMessageDialog(null, "Please enter the correct User ID & Password", "Error", JOptionPane.WARNING_MESSAGE);
			}
		}
		});
	}

}
