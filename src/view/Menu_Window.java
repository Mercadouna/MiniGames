package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControlador;

import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;

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
		setBounds(100, 100, 619, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGame_1_aim = new JButton("Aim Game");
		btnGame_1_aim.setBounds(68, 172, 126, 143);
		contentPane.add(btnGame_1_aim);
		ImageIcon icono = new ImageIcon("/iconos/Bullseye-Img.png"); // Ruta de la imagen
		btnGame_1_aim.setIcon(icono);
		//btnGame_1_aim.setIcon(new ImageIcon(getClass().getResource("/icons/Bullseye-Image.png")));
		JButton btnTrophyRoom = new JButton("Trophy room");
		btnTrophyRoom.setBounds(139, 444, 261, 57);
		contentPane.add(btnTrophyRoom);
		
		JButton btnGame_2_math = new JButton("Math Game");
		btnGame_2_math.setBounds(365, 172, 126, 143);
		contentPane.add(btnGame_2_math);
		
		JButton btnLOGOUT = new JButton("Log Out");
		btnLOGOUT.setBounds(296, 11, 132, 43);
		contentPane.add(btnLOGOUT);
		
		JButton btnERASEACC = new JButton("Erase Account");
		btnERASEACC.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
		btnERASEACC.setForeground(new Color(255, 0, 0));
		btnERASEACC.setBounds(461, 11, 132, 43);
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
