package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;
import java.awt.Label;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Trophy_Window extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnMenu;
	private JButton btnBuy_1;
	private JLabel lblImage_1;
	private JButton btnBuy_2;
	private	JButton btnBuy_3;
	private JButton btnBuy_4;
	private JButton btnBuy_5;
	private JButton btnBuy_6;
	private JLabel lblImage_2;
	private JLabel lblImage_3;
	private JLabel lblImage_4;
	private JLabel lblImage_5;
	private JLabel lblImage_6;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Trophy_Window dialog = new Trophy_Window();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Trophy_Window() {
		setBounds(100, 100, 836, 490);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		btnBuy_1 = new JButton("New button");
		btnBuy_1.setBounds(62, 144, 89, 23);
		contentPanel.add(btnBuy_1);
		btnBuy_1.addActionListener((ActionListener) this);
		
		btnBuy_2 = new JButton("New button");
		btnBuy_2.setBounds(321, 144, 89, 23);
		contentPanel.add(btnBuy_2);
		btnBuy_2.addActionListener((ActionListener) this);
		
		btnBuy_3 = new JButton("New button");
		btnBuy_3.setBounds(569, 144, 89, 23);
		contentPanel.add(btnBuy_3);
		btnBuy_3.addActionListener((ActionListener) this);
		
		btnBuy_4 = new JButton("New button");
		btnBuy_4.setBounds(62, 399, 89, 23);
		contentPanel.add(btnBuy_4);
		btnBuy_4.addActionListener((ActionListener) this);
		
		btnBuy_5 = new JButton("New button");
		btnBuy_5.setBounds(321, 399, 89, 23);
		contentPanel.add(btnBuy_5);
		btnBuy_5.addActionListener((ActionListener) this);
		
		btnBuy_6 = new JButton("New button");
		btnBuy_6.addActionListener((ActionListener) this);
		btnBuy_6.setBounds(550, 399, 89, 23);
		contentPanel.add(btnBuy_6);
		
		JLabel lblImage_1 = new JLabel("Imagen 1");
		lblImage_1.setBounds(31, 31, 150, 93);
		contentPanel.add(lblImage_1);
		
		JLabel lblImage_2 = new JLabel("Imagen 2");
		lblImage_2.setBounds(260, 31, 150, 93);
		contentPanel.add(lblImage_2);
		
		JLabel lblImage_3 = new JLabel("Imagen 3");
		lblImage_3.setBounds(550, 41, 150, 93);
		contentPanel.add(lblImage_3);
		
		JLabel lblImage_4 = new JLabel("Imagen 4");
		lblImage_4.setBounds(31, 280, 150, 93);
		contentPanel.add(lblImage_4);
		
		JLabel lblImage_5 = new JLabel("Imagen 5");
		lblImage_5.setBounds(288, 280, 150, 93);
		contentPanel.add(lblImage_5);
		
		JLabel lblImage_6 = new JLabel("Imagen 6");
		lblImage_6.setBounds(550, 280, 150, 93);
		contentPanel.add(lblImage_6);
		
		btnMenu = new JButton("Menu");
		btnMenu.setBounds(708, 422, 85, 21);
		contentPanel.add(btnMenu);
		btnMenu.addActionListener((ActionListener) this);
	}
}
