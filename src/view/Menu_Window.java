package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControlador;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.util.random.*;

public class Menu_Window extends JFrame  implements ActionListener{

	private JPanel contentPane;
	private LoginControlador cont;
	private JButton btnGame_2_math;
	private JButton btnTrophyRoom;
	private JButton btnGame_1_aim;
	
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
		
		btnGame_1_aim = new JButton("Aim Game");
		btnGame_1_aim.setIcon(new ImageIcon("C:\\Users\\20ala\\Downloads\\Logo+Mercadona+Web.png"));
		btnGame_1_aim.setBounds(53, 104, 126, 143);
		contentPane.add(btnGame_1_aim);
		btnGame_1_aim.addActionListener(this);
		
		btnTrophyRoom = new JButton("Trophy room");
		btnTrophyRoom.setBounds(533, 377, 261, 57);
		contentPane.add(btnTrophyRoom);
		btnTrophyRoom.addActionListener(this);
		
		btnGame_2_math = new JButton("Math Game");
		btnGame_2_math.setBounds(310, 104, 126, 143);
		contentPane.add(btnGame_2_math);
		btnGame_2_math.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnGame_1_aim) {
			
			Game_1_Window g1 = new Game_1_Window (this.cont);
			g1.setVisible(true);
			
			
		}
	}
}
