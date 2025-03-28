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

public class Trophy_Window extends JDialog {

	private final JPanel contentPanel = new JPanel();

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
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnT_1 = new JButton("New button");
		btnT_1.setBounds(62, 144, 89, 23);
		contentPanel.add(btnT_1);
		
		JButton btnT_2 = new JButton("New button");
		btnT_2.setBounds(321, 144, 89, 23);
		contentPanel.add(btnT_2);
		
		JButton btnT_3 = new JButton("New button");
		btnT_3.setBounds(632, 144, 89, 23);
		contentPanel.add(btnT_3);
		
		JButton btnT_4 = new JButton("New button");
		btnT_4.setBounds(62, 399, 89, 23);
		contentPanel.add(btnT_4);
		
		JButton btnT_5 = new JButton("New button");
		btnT_5.setBounds(321, 399, 89, 23);
		contentPanel.add(btnT_5);
		
		JButton btnT_6 = new JButton("New button");
		btnT_6.setBounds(632, 399, 89, 23);
		contentPanel.add(btnT_6);
	}
}
