package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControlador;
import model.Player;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.util.random.*;

public class Menu_Window extends JFrame  implements ActionListener{
	
	/**
	 * Create the frame.
	 * @param j 
	 * @param cont 
	 */
	
	private JPanel contentPane;
	private LoginControlador cont;
	private JButton btnGame_2_math;
	private JButton btnTrophyRoom;
	private JButton btnGame_1_aim;
	private JButton btnRecord;
	private JButton btnDelete;
	private JButton btnLogOut;
	private Player j;
	/**
	 * Create the frame.
	 * @param cont 
	 */
	public Menu_Window(LoginControlador controler, Player j) {
		this.cont =controler;
		this.j = j;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 539);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnGame_1_aim = new JButton("Aim Game");
		btnGame_1_aim.setBounds(56, 118, 126, 143);
		btnGame_1_aim.setIcon(new ImageIcon("C:\\Users\\20ala\\Downloads\\Logo+Mercadona+Web.png"));
		contentPane.add(btnGame_1_aim);
		btnGame_1_aim.addActionListener(this);
		
		btnTrophyRoom = new JButton("Trophy room");
		btnTrophyRoom.setBounds(133, 335, 261, 57);
		contentPane.add(btnTrophyRoom);
		btnTrophyRoom.addActionListener(this);
		
		btnGame_2_math = new JButton("Math Game");
		btnGame_2_math.setBounds(324, 118, 126, 143);
		contentPane.add(btnGame_2_math);
		
		btnRecord = new JButton("Record");
		btnRecord.setBounds(25, 37, 126, 34);
		contentPane.add(btnRecord);
		btnRecord.addActionListener(this);
		
		btnDelete = new JButton("Delete user");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.setBounds(409, 37, 112, 34);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(this);
		
		btnLogOut = new JButton("Log out");
		btnLogOut.setBounds(25, 471, 85, 21);
		contentPane.add(btnLogOut);
		btnLogOut.addActionListener(this);
		btnGame_2_math.addActionListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnGame_1_aim) {
			j.setPoints(cont.obtpoints(j));
			cont.modificarpuntos(j);
			Game_1_Window g1 = new Game_1_Window (this.cont);
			
			
			g1.setVisible(true);
			
			
		}
	}
}
