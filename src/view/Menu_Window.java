package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControlador;

import javax.swing.JButton;

public class Menu_Window extends JFrame  implements ActionListener{

	private JPanel contentPane;
	private LoginControlador cont;

	/**
	 * Create the frame.
	 * @param cont 
	 */
	public Menu_Window(LoginControlador controler) {
		this.cont =controler;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 843, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGame_1_aim = new JButton("Aim Game");
		btnGame_1_aim.setBounds(55, 104, 126, 143);
		contentPane.add(btnGame_1_aim);
		
		JButton btnTrophyRoom = new JButton("Trophy room");
		btnTrophyRoom.setBounds(533, 377, 261, 57);
		contentPane.add(btnTrophyRoom);
		
		JButton btnGame_2_math = new JButton("Math Game");
		btnGame_2_math.setBounds(310, 104, 126, 143);
		contentPane.add(btnGame_2_math);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
