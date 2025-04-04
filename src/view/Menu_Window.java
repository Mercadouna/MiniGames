package view;

import java.awt.*; // Import general AWT
import java.awt.event.*; // Import para eventos
import javax.swing.*; // Import general Swing
import javax.swing.border.*; // Import para bordes
import controlador.LoginControler;
import model.Player;

// Implementa ActionListener para manejar clics de botón
public class Menu_Window extends JFrame implements ActionListener {

    // --- Variables de Instancia ---
    private JPanel contentPane;
    private LoginControler cont;
    private Player j;

    // Componentes UI
    private JButton btnGame_1_aim, btnGame_2_math, btnTrophyRoom, btnRecord, btnDelete, btnLogOut;

    // Estilos (Colores y Fuentes)
    private Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
    private Font buttonFont = new Font("Segoe UI", Font.PLAIN, 16);
    private Color darkPurple = new Color(48, 25, 52);
    private Color mediumPurple = new Color(102, 51, 153);
    private Color lightPurple = new Color(204, 153, 255);
    private Color textColor = new Color(240, 240, 240);

    // Iconos (Cargados de forma segura)
    private ImageIcon aimIcon = loadIcon("/images/aim.png");
    private ImageIcon mathIcon = loadIcon("/images/math.png");
    private ImageIcon trophyIcon = loadIcon("/images/trophy.png");
    private ImageIcon recordIcon = loadIcon("/images/record.png");
    private ImageIcon userIcon = loadIcon("/images/user.png");
    private ImageIcon logoutIcon = loadIcon("/images/logout.png");
    // --- Fin Variables de Instancia ---


    /**
     * Constructor por defecto (VACÍO).
     * Necesario para que WindowBuilder pueda instanciar la clase en el editor visual.
     */
    public Menu_Window() {
        System.out.println("Constructor por defecto de Menu_Window llamado (probablemente por WindowBuilder)");
        // No inicializar la UI aquí para evitar errores con 'cont' y 'j' nulos.
    }

    /**
     * Constructor principal utilizado por la aplicación.
     * @param controler El controlador de login.
     * @param j El jugador que ha iniciado sesión.
     */
    public Menu_Window(LoginControler controler, Player j) {
        if (controler == null || j == null) {
            throw new IllegalArgumentException("El controlador y el jugador no pueden ser nulos.");
        }
        this.cont = controler;
        this.j = j;
        initializeUI(); // Llama al método que construye la interfaz gráfica
    }

    /**
     * Inicializa y configura todos los componentes de la UI.
     */
    private void initializeUI() {
        setTitle("Game Center - " + j.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Panel principal con fondo degradado
        contentPane = new JPanel(new BorderLayout(0, 30)) { // BorderLayout con VGap
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create(); // Usar create() para no modificar el original
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, darkPurple, getWidth(), getHeight(), mediumPurple);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose(); // Liberar contexto gráfico
            }
        };
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding
        setContentPane(contentPane);

        // Panel de título
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Centrar título
        titlePanel.setOpaque(false);
        JLabel titleLabel = new JLabel("GAME CENTER");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(textColor);
        titlePanel.add(titleLabel);
        contentPane.add(titlePanel, BorderLayout.NORTH);

        // Panel central con los botones
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new GridLayout(2, 2, 30, 30)); // Grid 2x2 con gaps

        // --- Creación de botones ---
        // Botones con código "Inline" para compatibilidad con WindowBuilder
        // (basado en el error reportado anteriormente)

        // --- INLINE para btnGame_1_aim ---
        btnGame_1_aim = new JButton("Aim Game", aimIcon);
        btnGame_1_aim.setFont(buttonFont);
        btnGame_1_aim.setForeground(textColor);
        btnGame_1_aim.setBackground(mediumPurple);
        btnGame_1_aim.setFocusPainted(false);
        btnGame_1_aim.setBorder(new CompoundBorder(
            new LineBorder(lightPurple, 1),
            new EmptyBorder(15, 25, 15, 25)
        ));
        btnGame_1_aim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnGame_1_aim.setHorizontalTextPosition(SwingConstants.CENTER);
        btnGame_1_aim.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnGame_1_aim.setIconTextGap(10);
        btnGame_1_aim.addMouseListener(createHoverMouseAdapter(btnGame_1_aim));
        // --- FIN INLINE ---

        // Otros botones usando el helper (se podrían poner inline si dan problemas)
        btnGame_2_math = createButton("Math Game", mathIcon);
        btnTrophyRoom = createButton("Trophy Room", trophyIcon);
        btnRecord = createButton("Player Records", recordIcon);

        // Añadir botones al panel central
        centerPanel.add(btnGame_1_aim);
        centerPanel.add(btnGame_2_math);
        centerPanel.add(btnTrophyRoom);
        centerPanel.add(btnRecord);
        contentPane.add(centerPanel, BorderLayout.CENTER);

        // Panel inferior con botones de usuario
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        bottomPanel.setOpaque(false);

        // --- INLINE para btnDelete ---
        btnDelete = new JButton("Delete Account", userIcon);
        btnDelete.setFont(buttonFont);
        btnDelete.setForeground(textColor);
        btnDelete.setBackground(mediumPurple);
        btnDelete.setFocusPainted(false);
        btnDelete.setBorder(new CompoundBorder(
            new LineBorder(lightPurple, 1),
            new EmptyBorder(10, 20, 10, 20) // Padding ajustado
        ));
        btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDelete.setHorizontalTextPosition(SwingConstants.RIGHT); // Icono a la izquierda
        btnDelete.setVerticalTextPosition(SwingConstants.CENTER);
        btnDelete.setIconTextGap(10);
        btnDelete.addMouseListener(createHoverMouseAdapter(btnDelete));
        // --- FIN INLINE ---

        btnLogOut = createButton("Log Out", logoutIcon); // Usando helper

        // Añadir botones al panel inferior
        bottomPanel.add(btnDelete);
        bottomPanel.add(btnLogOut);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        // Asignar listeners a todos los botones
        addAllActionListeners();

        pack(); // Ajustar tamaño de la ventana
        setLocationRelativeTo(null); // Centrar en pantalla
    }

    /**
     * Añade los ActionListeners a los botones.
     * Se llama solo desde el constructor principal (a través de initializeUI).
     */
    private void addAllActionListeners() {
        btnGame_1_aim.addActionListener(this);
        btnGame_2_math.addActionListener(this);
        btnTrophyRoom.addActionListener(this);
        btnRecord.addActionListener(this);
        btnDelete.addActionListener(this);
        btnLogOut.addActionListener(this);
    }

    /**
     * Método helper para crear y estilizar botones (usado por botones no inlinados).
     */
    private JButton createButton(String text, ImageIcon icon) {
        JButton button = new JButton(text, icon);
        button.setFont(buttonFont);
        button.setForeground(textColor);
        button.setBackground(mediumPurple);
        button.setFocusPainted(false);
        button.setBorder(new CompoundBorder(
            new LineBorder(lightPurple, 1),
            new EmptyBorder(15, 25, 15, 25) // Padding estándar para estos botones
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setIconTextGap(10);
        // Aplicar efecto hover usando helper
        button.addMouseListener(createHoverMouseAdapter(button));
        return button;
    }

    /**
     * Método helper para crear el MouseAdapter del efecto hover.
     */
    private MouseAdapter createHoverMouseAdapter(final JButton button) {
         return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(lightPurple);
                button.setForeground(darkPurple);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(mediumPurple);
                button.setForeground(textColor);
            }
        };
    }

    /**
     * Método helper para cargar iconos de forma segura.
     */
    private ImageIcon loadIcon(String path) {
        try {
            java.net.URL imgURL = getClass().getResource(path);
            if (imgURL != null) {
                return new ImageIcon(imgURL);
            } else {
                System.err.println("Error: No se pudo encontrar el recurso de imagen: " + path);
                return null; // Devolver null si no se encuentra
            }
        } catch (Exception e) {
            System.err.println("Error al cargar icono: " + path + " - " + e.getMessage());
            return null; // Devolver null en caso de error
        }
    }

    /**
     * Manejador de eventos para todos los botones.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
         // Comprobación inicial por si acaso
         if (cont == null || j == null) {
              System.err.println("Error crítico: Controlador o Jugador es null en actionPerformed.");
              JOptionPane.showMessageDialog(this, "Error interno. Por favor, reinicie.", "Error", JOptionPane.ERROR_MESSAGE);
              // Considerar volver al login o cerrar
              // System.exit(1); // Opcion drástica
              return;
         }

        Object source = e.getSource();

        if (source == btnGame_1_aim) {
            // Abrir ventana del juego (sin modificar puntos aquí)
             System.out.println("Abriendo Aim Game para: " + j.getName());
            Game_1_Window g1 = new Game_1_Window(this.cont, this.j);
            g1.setLocationRelativeTo(this); // Centrar respecto al menú
            g1.setVisible(true); // Es modal, bloqueará esta ventana

        } else if (source == btnGame_2_math) {
            JOptionPane.showMessageDialog(this, "Juego de Matemáticas - ¡No implementado!", "Próximamente", JOptionPane.INFORMATION_MESSAGE);

        } else if (source == btnTrophyRoom) {
            // --- Actualizar puntos ANTES de abrir TrophyWindow ---
            try {
                int puntosActualizados = cont.obtpoints(j);
                j.setPoints(puntosActualizados);
                System.out.println("Puntos actualizados a " + j.getPoints() + " antes de abrir Trophy_Window.");
            } catch (Exception ex) {
                System.err.println("Error al obtener puntos actualizados: " + ex.getMessage());
                 JOptionPane.showMessageDialog(this, "No se pudieron actualizar los puntos.\nSe mostrará el último valor conocido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            // --- Fin actualización ---
            Trophy_Window trophyWindow = new Trophy_Window(j, cont);
            // trophyWindow.setVisible(true); // Es modal, setVisible es llamado por el constructor implícitamente? No, hay que llamarlo.
             trophyWindow.setLocationRelativeTo(this); // Centrar
             trophyWindow.setVisible(true); // Hacer visible
             // No cerramos Menu_Window aquí, Trophy_Window es un JDialog modal

        } else if (source == btnRecord) {
             System.out.println("Abriendo Estadísticas para: " + j.getName());
            Stats_plays_Window spw = new Stats_plays_Window(cont, j);
            spw.setLocationRelativeTo(this); // Centrar
            spw.setVisible(true);
            // No cerramos Menu_Window aquí, Stats_plays_Window debería ser DISPOSE_ON_CLOSE

        } else if (source == btnDelete) {
            int option = JOptionPane.showConfirmDialog(
                this,
                "¿Estás MUY seguro de que quieres ELIMINAR tu cuenta?\n¡¡Esta acción es PERMANENTE!!",
                "Confirmar Eliminación Definitiva",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {
                 try {
                     System.out.println("Eliminando cuenta para: " + j.getName());
                    cont.deletePlayer(j);
                    JOptionPane.showMessageDialog(this, "Cuenta eliminada con éxito.", "Eliminación Completa", JOptionPane.INFORMATION_MESSAGE);
                    // Volver a Login
                    Login_Window loginWindow = new Login_Window(cont);
                    loginWindow.setVisible(true);
                    this.dispose(); // Cerrar ventana de Menú
                 } catch (Exception ex) {
                     System.err.println("Error al eliminar jugador: " + ex.getMessage());
                     ex.printStackTrace(); // Para depuración
                     JOptionPane.showMessageDialog(this, "Error al eliminar la cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
                 }
            }
        } else if (source == btnLogOut) {
            int option = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que quieres cerrar sesión?",
                "Confirmar Cierre de Sesión",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {
                 System.out.println("Cerrando sesión para: " + j.getName());
                Login_Window loginWindow = new Login_Window(cont);
                loginWindow.setVisible(true);
                this.dispose(); // Cerrar ventana de Menú
            }
        }
    }

} // Fin clase Menu_Window