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
import javax.swing.JOptionPane; // Importar para mensajes de éxito
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
    private JButton btnSingUp = new JButton("Sign Up");
    private JButton btnBack = new JButton("Back");
    private LoginControler cont;
    private JTextField textField;
    private JLabel lblErrorMessage; // Variable de instancia

    /**
     * Create the frame.
     */
    public Register_Window(LoginControler controler) {
        this.cont = controler;
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 600);
        setResizable(false);

        // --- Panel Principal ---
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(128, 0, 128), 0, getHeight(), new Color(200, 100, 200));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null); // Mantener null layout
        setContentPane(contentPane);

        // --- Panel de Registro Interno ---
        JPanel registerPanel = new JPanel();
        registerPanel.setBounds(50, 100, 300, 400);
        registerPanel.setBackground(new Color(255, 255, 255, 200)); // Fondo semi-transparente
        registerPanel.setLayout(null); // Layout nulo
        // registerPanel.setOpaque(true); // Es true por defecto, pero puedes forzarlo si sospechas
        contentPane.add(registerPanel); // Añadir al panel principal

        // --- Componentes dentro de registerPanel ---
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

        // Estilos Botones
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

        // --- Etiqueta de Mensaje de Error (Inicializada y Añadida con más altura) ---
        lblErrorMessage = new JLabel("");
        lblErrorMessage.setForeground(Color.RED);
        lblErrorMessage.setFont(new Font("Arial", Font.BOLD, 11));
        lblErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
        // <<< --- ALTURA AUMENTADA --- >>>
        lblErrorMessage.setBounds(30, 330, 240, 40); // Aumentada la altura a 40
        registerPanel.add(lblErrorMessage);

        // Añadir Listeners
        btnSingUp.addActionListener(this);
        btnBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Limpiar mensaje de error previo
        lblErrorMessage.setText("");

        if (e.getSource() == btnSingUp) {
            // 1. Obtener datos y limpiar espacios
            String username = textField.getText().trim();
            String password = new String(pwfpasswrd.getPassword());

            // 2. Realizar Validaciones
            boolean usernameVacio = username.isEmpty();
            boolean passwordCorta = password.length() < 3;

            // Ajustar mensajes y lógica de validación si es necesario
            if (usernameVacio && passwordCorta) {
                lblErrorMessage.setText("<html><center>Usuario vacío y contraseña<br>muy corta (min. 3).</center></html>"); // Ejemplo HTML
                return;
            } else if (usernameVacio) {
                lblErrorMessage.setText("El nombre de usuario no puede estar vacío.");
                return;
            } else if (passwordCorta) {
                lblErrorMessage.setText("<html>La contraseña debe tener <br>al menos 3 caracteres.</html>");
                return;
            }

            // 3. Comprobar existencia si validaciones pasan
            Player playerParaComprobar = new Player(username, "", 0);
            boolean existe = cont.checkPL(playerParaComprobar);

            if (existe) {
                lblErrorMessage.setText("El nombre de usuario '" + username + "' ya existe.");
            } else {
                // 4. Registrar usuario
                Player nuevoJugador = new Player(username, password, 0);
                cont.addplayer(nuevoJugador);

                // Obtener puntos iniciales
                int playerPoints = cont.obtpoints(nuevoJugador);
                nuevoJugador.setPoints(playerPoints);

                JOptionPane.showMessageDialog(this,
                                             "¡Usuario '" + username + "' registrado con éxito!",
                                             "Registro Completo",
                                             JOptionPane.INFORMATION_MESSAGE);

                Menu_Window mw = new Menu_Window(cont, nuevoJugador);
                mw.setVisible(true);
                this.dispose();
            }

        } else if (e.getSource() == btnBack) {
            Login_Window lw = new Login_Window(cont);
            lw.setVisible(true);
            this.dispose();
        }
    }
}