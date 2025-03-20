package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;

public class Login_Window extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textUsername;
	private JPasswordField passwordpasswrd;
	JButton btnLogIn = new JButton("Log In");
	JButton btnSingUp = new JButton("Sign up");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_Window frame = new Login_Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login_Window() {
		setTitle("Login_Minigames");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 168, 81));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textUsername = new JTextField();
		textUsername.setBounds(184, 62, 194, 20);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD, 17));
		lblNewLabel.setBounds(40, 65, 151, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setForeground(new Color(255, 255, 0));
		lblNewLabel_1.setFont(new Font("Snap ITC", Font.BOLD, 17));
		lblNewLabel_1.setBounds(40, 148, 151, 14);
		contentPane.add(lblNewLabel_1);
		
		passwordpasswrd = new JPasswordField();
		passwordpasswrd.setBounds(184, 145, 194, 20);
		contentPane.add(passwordpasswrd);
		
		
		btnLogIn.setBackground(new Color(255, 255, 0));
		btnLogIn.setForeground(new Color(255, 168, 81));
		btnLogIn.setFont(new Font("Snap ITC", Font.BOLD, 17));
		btnLogIn.setBounds(55, 213, 117, 23);
		contentPane.add(btnLogIn);
		
		
		btnSingUp.setBackground(new Color(255, 255, 0));
		btnSingUp.setForeground(new Color(255, 168, 81));
		btnSingUp.setFont(new Font("Snap ITC", Font.BOLD, 17));
		btnSingUp.setBounds(259, 213, 119, 23);
		contentPane.add(btnSingUp);
		btnLogIn.addActionListener(this);
		btnSingUp.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
