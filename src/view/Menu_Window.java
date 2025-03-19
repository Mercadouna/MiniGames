package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Menu_Window extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu_Window frame = new Menu_Window();
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
	public Menu_Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 843, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGame_1_aim = new JButton("Aim Game");
		btnGame_1_aim.setBounds(55, 104, 126, 143);
		contentPane.add(btnGame_1_aim);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(533, 377, 261, 57);
		contentPane.add(btnNewButton_1);
		
		JButton btnGame_2_math = new JButton("Math Game");
		btnGame_2_math.setBounds(310, 104, 126, 143);
		contentPane.add(btnGame_2_math);
	}
}
