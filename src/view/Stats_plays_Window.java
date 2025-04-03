package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controlador.LoginControler;
import model.Player;

import java.awt.List;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Button;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class Stats_plays_Window extends JFrame {

	private JPanel contentPane;
	private LoginControler cont;
	
	
	
	//Colors
    private Color darkPurple = new Color(48, 25, 52);
    private Color mediumPurple = new Color(102, 51, 153);
    private Color lightPurple = new Color(204, 153, 255);
    private Color textColor = new Color(240, 240, 240);
    //Buttons
    private JButton btnGoB;
    //icons
    private ImageIcon HomeIcon = new ImageIcon(getClass().getResource("/images/Home.png"));
    //Fonts
    private Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
    private Font buttonFont = new Font("Segoe UI", Font.PLAIN, 16);
	/**
	 * Launch the application.
	 */
	
    /**
	 * Create the frame.
	 */
	public Stats_plays_Window(LoginControler cont, Player j) {
		this.cont=cont;
		setTitle("Stats");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		// Panel principal con fondo degradado
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo degradado profesional
                GradientPaint gp = new GradientPaint(
                    0, 0, darkPurple, 
                    getWidth(), getHeight(), lightPurple);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        bottomPanel.setOpaque(false);
		
		btnGoB=createButton("Go Back", HomeIcon);
		bottomPanel.add(btnGoB);
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		
		JList list = new JList(cont.getPlays(j).toArray());
		list.setFont(new Font("OCR A Extended", Font.PLAIN, 17));
		JScrollPane scrollPane = new JScrollPane(list);

	    scrollPane.setBounds(32, 33, 489, 517);

	    contentPane.add(scrollPane);

        
        
		
	}
	private JButton createButton(String text, ImageIcon icon) {
        JButton button = new JButton(text, icon);
        button.setFont(buttonFont);
        button.setForeground(textColor);
        button.setBackground(mediumPurple);
        button.setFocusPainted(false);
        button.setBorder(new CompoundBorder(
            new LineBorder(lightPurple, 1),
            new EmptyBorder(15, 25, 15, 25)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setIconTextGap(10);
        
        // Efecto hover
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(lightPurple);
                button.setForeground(darkPurple);
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(mediumPurple);
                button.setForeground(textColor);
            }
        });
        
        return button;
    }
}
