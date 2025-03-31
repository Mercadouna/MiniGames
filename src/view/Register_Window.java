package view;

import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControler;
import model.Player;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.RenderingHints;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Cursor;

public class Register_Window extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JPasswordField pwfpasswrd;
    JButton btnSingUp = new JButton("Sign Up");
    JButton btnBack = new JButton("Back"); // Botón para volver al login
    private LoginControler cont;
    private JTextField textField;

    /**
     * Create the frame.
     */
    public Register_Window(LoginControler controler) {
        this.cont = controler;
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 600); // Ajustar tamaño para que coincida con Login_Window
        setResizable(false);
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(128, 0, 128), 0, getHeight(), new Color(200, 100, 200));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel registerPanel = new JPanel();
        registerPanel.setBounds(50, 100, 300, 400); // Ajustar posición y tamaño
        registerPanel.setBackground(new Color(255, 255, 255, 200));
        registerPanel.setLayout(null);
        contentPane.add(registerPanel);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Arial", Font.BOLD, 14));
        lblUsername.setBounds(30, 50, 80, 20);
        registerPanel.add(lblUsername);

        textField = new JTextField();
        textField.setBounds(30, 80, 240, 30);
        registerPanel.add(textField);
        textField.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
        lblPassword.setBounds(30, 130, 80, 20);
        registerPanel.add(lblPassword);

        pwfpasswrd = new JPasswordField();
        pwfpasswrd.setBounds(30, 160, 240, 30);
        registerPanel.add(pwfpasswrd);

        // Estilos para los botones
        btnSingUp.setBackground(new Color(128, 0, 128));
        btnSingUp.setForeground(Color.WHITE);
        btnSingUp.setFont(new Font("Arial", Font.BOLD, 16));
        btnSingUp.setBounds(30, 220, 240, 40);
        btnSingUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSingUp.setBorder(new LineBorder(new Color(100, 0, 100), 2));
        registerPanel.add(btnSingUp);
        
        btnBack.setBackground(new Color(128, 0, 128));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setBounds(30, 280, 240, 40);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.setBorder(new LineBorder(new Color(100, 0, 100), 2));
        registerPanel.add(btnBack);

        btnSingUp.addActionListener(this);
        btnBack.addActionListener(this); // Agregar listener para el botón "Back"
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean existe;
        if (e.getSource() == btnSingUp) {
            existe = cont.checkPL(new Player(textField.getText(), new String(pwfpasswrd.getPassword()), 0));
            if (!existe) {
                cont.addplayer(new Player(textField.getText(), new String(pwfpasswrd.getPassword()), 0));
                int playerPoints = cont.obtpoints(new Player(textField.getText(), new String(pwfpasswrd.getPassword()), 0));
                Menu_Window mw = new Menu_Window(cont, new Player(textField.getText(), new String(pwfpasswrd.getPassword()), playerPoints));
                mw.setVisible(true);
                this.dispose();
            } else {
                // Manejar usuario existente
            }
        }
        
        if (e.getSource() == btnBack) {
            Login_Window lw = new Login_Window(cont);
            lw.setVisible(true);
            this.dispose();
        }
    }
}