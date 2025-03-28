package view;

import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControlador;
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

public class Login_Window extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JPasswordField pwfpasswrd;
    JButton btnLogIn = new JButton("Login");
    JButton btnSingUp = new JButton("Sign Up");
    private LoginControler cont;
    private JTextField textField;
    private JLabel lblErrorMessage; // Etiqueta para el mensaje de error

    /**
     * Create the frame.
     */
    public Login_Window(LoginControler controler) {
        this.cont = controler;
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 600);
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

        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(50, 100, 300, 400);
        loginPanel.setBackground(new Color(255, 255, 255, 200));
        loginPanel.setLayout(null);
        contentPane.add(loginPanel);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Arial", Font.BOLD, 14));
        lblUsername.setBounds(30, 50, 80, 20);
        loginPanel.add(lblUsername);

        textField = new JTextField();
        textField.setBounds(30, 80, 240, 30);
        loginPanel.add(textField);
        textField.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
        lblPassword.setBounds(30, 130, 80, 20);
        loginPanel.add(lblPassword);

        pwfpasswrd = new JPasswordField();
        pwfpasswrd.setBounds(30, 160, 240, 30);
        loginPanel.add(pwfpasswrd);

        // Estilos para los botones
        btnLogIn.setBackground(new Color(128, 0, 128));
        btnLogIn.setForeground(Color.WHITE);
        btnLogIn.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogIn.setBounds(30, 220, 240, 40);
        btnLogIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogIn.setBorder(new LineBorder(new Color(100, 0, 100), 2));
        loginPanel.add(btnLogIn);

        btnSingUp.setBackground(new Color(128, 0, 128));
        btnSingUp.setForeground(Color.WHITE);
        btnSingUp.setFont(new Font("Arial", Font.BOLD, 16));
        btnSingUp.setBounds(30, 280, 240, 40);
        btnSingUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSingUp.setBorder(new LineBorder(new Color(100, 0, 100), 2));
        loginPanel.add(btnSingUp);

        // Etiqueta para el mensaje de error
        lblErrorMessage = new JLabel("");
        lblErrorMessage.setForeground(Color.RED);
        lblErrorMessage.setFont(new Font("Arial", Font.BOLD, 12));
        lblErrorMessage.setBounds(30, 330, 240, 20);
        loginPanel.add(lblErrorMessage);

        btnLogIn.addActionListener(this);
        btnSingUp.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogIn) {
            String username = textField.getText();
            String password = new String(pwfpasswrd.getPassword());
            Player j = new Player(username, password, 0);
            int playerPoints = cont.obtpoints(j);
            j.setPoints(playerPoints);

            if (cont.compareplayer(j) == true) {
                Menu_Window mw = new Menu_Window(cont, j);
                mw.setVisible(true);
                this.dispose();
            } else {
                lblErrorMessage.setText("Incorrect player or password"); // Mostrar mensaje de error
            }
        }

        if (e.getSource() == btnSingUp) {
            Register_Window mw = new Register_Window(cont);
            mw.setVisible(true);
            this.dispose();
        }
    }
}