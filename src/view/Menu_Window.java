package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControler;

import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class Menu_Window extends JFrame  implements ActionListener{

	private JPanel contentPane;
	private LoginControler cont;
	private JButton btnGame_1_aim = new JButton("Aim Game");

	private JButton btnERASEACC = new JButton("Erase Account");

	public Menu_Window(LoginControler controler) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu_Window.class.getResource("/images/game-controller-icon-illustration-free-vector.jpg")));
		this.cont =controler;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnGame_1_aim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGame_1_aim.setBounds(36, 101, 126, 143);
		contentPane.add(btnGame_1_aim);
		btnGame_1_aim.setIcon(new ImageIcon(Menu_Window.class.getResource("/images/AimGameIcon.png")));
		JButton btnTrophyRoom = new JButton("Trophy room");
		btnTrophyRoom.setBounds(513, 375, 261, 57);
		contentPane.add(btnTrophyRoom);
		
		JButton btnGame_2_math = new JButton("Math Game");
		btnGame_2_math.setBounds(218, 101, 126, 143);
		contentPane.add(btnGame_2_math);
		
		JButton btnLOGOUT = new JButton("Log Out");
		btnLOGOUT.setBounds(500, 11, 132, 43);
		contentPane.add(btnLOGOUT);
		
		JButton btnERASEACC = new JButton("Erase Account");
		btnERASEACC.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
		btnERASEACC.setForeground(new Color(255, 0, 0));
		btnERASEACC.setBounds(642, 11, 132, 43);
		contentPane.add(btnERASEACC);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 11, 159, 14);
		contentPane.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
