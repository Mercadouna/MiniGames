package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import controlador.LoginControler;
import model.Player;

public class Menu_Window extends JFrame implements ActionListener {

	private JPanel contentPane;
	private LoginControler cont;
	private Player j;

	private JButton btnGame_1_aim, btnGame_2_math, btnTrophyRoom, btnRecord, btnDelete, btnLogOut;

	private Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
	private Font buttonFont = new Font("Segoe UI", Font.PLAIN, 16);

	private Color darkPurple = new Color(48, 25, 52);
	private Color mediumPurple = new Color(102, 51, 153);
	private Color lightPurple = new Color(204, 153, 255);
	private Color textColor = new Color(240, 240, 240);

	private ImageIcon aimIcon = new ImageIcon(getClass().getResource("/images/aim.png"));
	private ImageIcon mathIcon = new ImageIcon(getClass().getResource("/images/math.png"));
	private ImageIcon trophyIcon = new ImageIcon(getClass().getResource("/images/trophy.png"));
	private ImageIcon recordIcon = new ImageIcon(getClass().getResource("/images/record.png"));
	private ImageIcon userIcon = new ImageIcon(getClass().getResource("/images/user.png"));
	private ImageIcon logoutIcon = new ImageIcon(getClass().getResource("/images/logout.png"));

	public Menu_Window(LoginControler controler, Player j) {
		this.cont = controler;
		this.j = j;

		setTitle("Game Center - " + j.getName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);

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
						getWidth(), getHeight(), mediumPurple);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setLayout(new BorderLayout(0, 30));
		setContentPane(contentPane);

		// Panel de título
		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		JLabel titleLabel = new JLabel("GAME CENTER");
		titleLabel.setFont(titleFont);
		titleLabel.setForeground(textColor);
		titlePanel.add(titleLabel);
		contentPane.add(titlePanel, BorderLayout.NORTH);

		// Panel central con los botones
		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		centerPanel.setLayout(new GridLayout(2, 2, 30, 30));

		// Creación de botones con estilo profesional
		btnGame_1_aim = createButton("Aim Game", aimIcon);
		btnGame_2_math = createButton("Math Game", mathIcon);
		btnTrophyRoom = createButton("Trophy Room", trophyIcon);
		btnRecord = createButton("Player Records", recordIcon);

		centerPanel.add(btnGame_1_aim);
		centerPanel.add(btnGame_2_math);
		centerPanel.add(btnTrophyRoom);
		centerPanel.add(btnRecord);

		contentPane.add(centerPanel, BorderLayout.CENTER);

		// Panel inferior con botones de usuario
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		bottomPanel.setOpaque(false);

		btnDelete = createButton("Delete Account", userIcon);
		btnLogOut = createButton("Log Out", logoutIcon);

		bottomPanel.add(btnDelete);
		bottomPanel.add(btnLogOut);

		contentPane.add(bottomPanel, BorderLayout.SOUTH);

		// Asignar listeners
		btnGame_1_aim.addActionListener(this);
		btnGame_2_math.addActionListener(this);
		btnTrophyRoom.addActionListener(this);
		btnRecord.addActionListener(this);
		btnDelete.addActionListener(this);
		btnLogOut.addActionListener(this);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnGame_1_aim) {
        	int sc =cont.obtpoints(j);
            j.setPoints(sc);
            cont.modifypoints(j, "AIM");
            Game_1_Window g1 = new Game_1_Window(this.cont);
            g1.setVisible(true);
        }
        else if(e.getSource() == btnLogOut) {
            int option = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you want to log out?", 
                "Confirm Logout", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
                
            if(option == JOptionPane.YES_OPTION) {
                Login_Window loginWindow = new Login_Window(cont);
                loginWindow.setVisible(true);
                this.dispose();
            }
        }
		else if(e.getSource()==btnDelete) {
			cont.deletePlayer(j);
			Login_Window loginWindow = new Login_Window(cont);
			loginWindow.setVisible(true);
			this.dispose();

		}
		else if (e.getSource() == btnTrophyRoom) {
			Trophy_Window trophyWindow = new Trophy_Window(j, cont);
			trophyWindow.setVisible(true);
			this.dispose();

		}
		else if(e.getSource()==btnRecord) {
			Stats_plays_Window spw= new Stats_plays_Window(cont, j);
			spw.setVisible(true);
			this.dispose();
		}
		// Aquí puedes agregar las acciones para los demás botones
	}
}