package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControlador;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

public class Game_1_Window extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Game_1_Window dialog = new Game_1_Window(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param cont 
	 */
	public Game_1_Window(LoginControlador cont) {
		setBounds(100, 100, 814, 499);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 1, 460);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JButton btnNewButton = new JButton("New button");
			btnNewButton.setBounds(278, 135, 218, 170);
			contentPanel.add(btnNewButton);
		}
		
		JLabel lblNewLabel = new JLabel("Juego Completado");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblNewLabel.setBounds(215, 84, 342, 254);
		getContentPane().add(lblNewLabel);
	}
}
