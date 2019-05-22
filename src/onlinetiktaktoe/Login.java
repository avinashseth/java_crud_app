package onlinetiktaktoe;

import utilities.Constants;
import utilities.User;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import database.Dbconnector;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frmTikTakToe;
	private JTextField tf_username;
	private JPasswordField pf_password;
	
	private Dbconnector dbc;
	
	public List<User> users = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmTikTakToe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
//		System.out.println(Constants.APP_NAME);
//		Constants.APP_NAME = "Evil compeititor";
//		System.out.println(Constants.APP_NAME);
		initialize();
		dbc = new Dbconnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTikTakToe = new JFrame();
		frmTikTakToe.setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\ust_workspace\\onlinetiktaktoe\\assets\\app_icon.png"));
		frmTikTakToe.setTitle("dNote Keeper <avinash.seth@outlook.com>");
		frmTikTakToe.setBounds(100, 100, 450, 380);
		frmTikTakToe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmTikTakToe.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblOnlineTikTak = new JLabel("dNote Keeper");
		lblOnlineTikTak.setFont(new Font("Dialog", Font.BOLD, 18));
		GridBagConstraints gbc_lblOnlineTikTak = new GridBagConstraints();
		gbc_lblOnlineTikTak.insets = new Insets(0, 0, 5, 0);
		gbc_lblOnlineTikTak.gridx = 0;
		gbc_lblOnlineTikTak.gridy = 1;
		frmTikTakToe.getContentPane().add(lblOnlineTikTak, gbc_lblOnlineTikTak);
		
		JLabel lblUsername = new JLabel("Username");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 0);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 3;
		frmTikTakToe.getContentPane().add(lblUsername, gbc_lblUsername);
		
		tf_username = new JTextField();
		GridBagConstraints gbc_tf_username = new GridBagConstraints();
		gbc_tf_username.insets = new Insets(0, 0, 5, 0);
		gbc_tf_username.gridx = 0;
		gbc_tf_username.gridy = 4;
		frmTikTakToe.getContentPane().add(tf_username, gbc_tf_username);
		tf_username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 0);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 5;
		frmTikTakToe.getContentPane().add(lblPassword, gbc_lblPassword);
		
		pf_password = new JPasswordField();
		pf_password.setEchoChar('*');
		pf_password.setColumns(10);
		GridBagConstraints gbc_pf_password = new GridBagConstraints();
		gbc_pf_password.anchor = GridBagConstraints.NORTH;
		gbc_pf_password.insets = new Insets(0, 0, 5, 0);
		gbc_pf_password.gridx = 0;
		gbc_pf_password.gridy = 6;
		frmTikTakToe.getContentPane().add(pf_password, gbc_pf_password);
		
		JButton btn_login = new JButton("Login");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = tf_username.getText().toString();
				String password = pf_password.getText().toString();
				if(email.length() < 1 || password.length() < 1) {
					String message = "Email & Password, both fields are required!";
					    JOptionPane.showMessageDialog(new JFrame(), message, "Oops",
					        JOptionPane.ERROR_MESSAGE);
				} else {
//					users = dbc.readAllUsers();
//					for(int i = 0; i < users.size(); i++) {
//						System.out.println("UserId:" + users.get(i).getId());
//					}
					int userId = dbc.loginUser(email, password);
					if(userId > 0) {
						frmTikTakToe.setVisible(false);
						Viewnotes vn = new Viewnotes(userId);
					} else {
						System.out.println("not found");
					}
				}
			}
		});
		GridBagConstraints gbc_btn_login = new GridBagConstraints();
		gbc_btn_login.insets = new Insets(0, 0, 5, 0);
		gbc_btn_login.gridx = 0;
		gbc_btn_login.gridy = 7;
		frmTikTakToe.getContentPane().add(btn_login, gbc_btn_login);
		
		JMenuBar menuBar = new JMenuBar();
		frmTikTakToe.setJMenuBar(menuBar);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
	}

}
