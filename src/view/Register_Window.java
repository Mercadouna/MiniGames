package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControlador;
import model.Player;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Register_Window extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPasswordField pwfpasswrd;
	JButton btnSingUp = new JButton("Sign up");
	private LoginControlador cont;
	private JTextField textField;
	private JLabel lblMSG;
	/**
	 * Create the frame.
	 */
	public Register_Window(LoginControlador controler) {
		this.cont=controler;
		setTitle("Register_Minigames");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 168, 81));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel.setBounds(40, 65, 151, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		lblNewLabel_1.setBounds(40, 148, 151, 14);
		contentPane.add(lblNewLabel_1);
		
		pwfpasswrd = new JPasswordField();
		pwfpasswrd.setBounds(184, 145, 194, 20);
		contentPane.add(pwfpasswrd);
		btnSingUp.setForeground(new Color(255, 128, 0));
		btnSingUp.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		btnSingUp.setBounds(259, 213, 119, 23);
		contentPane.add(btnSingUp);
		
		textField = new JTextField();
		textField.setBounds(184, 65, 194, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblMSG = new JLabel("");
		lblMSG.setHorizontalAlignment(SwingConstants.CENTER);
		lblMSG.setBounds(62, 22, 289, 14);
		contentPane.add(lblMSG);
		btnSingUp.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
		if(e.getSource()==btnSingUp) {
			if(!cont.compareplayer(new Player(textField.getText(),new String(pwfpasswrd.getPassword()),0))) {
				cont.addplayer(new Player(textField.getText(),new String(pwfpasswrd.getPassword()),0));
				Menu_Window mw= new Menu_Window(this.cont);
				mw.setVisible(true);
				this.dispose();
			}else {
				lblMSG.setText("This account already exists.");
			}
		}
	}
}
