package view;

import java.awt.BorderLayout; // Aunque no se use en contentPane, puede ser útil importar
import java.awt.FlowLayout;  // Para el panel inferior si se usara
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random; // Para puntos aleatorios si decides usarlos

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControler;
import model.Player; // Importar Player

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics; // Para createPlaceholderIcon
import javax.swing.SwingConstants;
import javax.swing.JOptionPane; // Para mensajes de error

// No necesita implementar ActionListener directamente si usa clases anónimas
public class Game_2_Window extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private LoginControler cont; // Variable para el controlador
    private Player player;     // Variable para el jugador
    private static final String GAME_NAME = "ARITMETICS"; // Nombre del juego como en la BD
    private static final int POINTS_EARNED_MATH = 50; // Puntos fijos para este juego (ejemplo)

    /**
     * Constructor que recibe LoginControler y Player.
     */
    public Game_2_Window(LoginControler cont, Player player) {
        // Guardar referencias
        if (cont == null || player == null) {
            throw new IllegalArgumentException("LoginControler y Player no pueden ser null.");
        }
        this.cont = cont;
        this.player = player;

        // Configuración ventana
        setTitle("Math Game - Completado");
        setModal(true); // Hacerla modal para bloquear el menú
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 500); // Tamaño fijo

        // Panel de contenido
        // NOTA: Sigues usando null layout aquí, lo cual no es recomendable
        // para mantenibilidad, pero se respeta tu código original.
        getContentPane().setLayout(null); // Layout nulo para el Frame/Dialog principal
        contentPanel.setBounds(0, 0, 800, 500); // Ocupa todo el frame
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(null); // Layout nulo también para el panel interno
        getContentPane().add(contentPanel);


        // Cargar el GIF
        ImageIcon gifIcon = loadIcon("/images/MathGame.gif"); // Usar helper
        JLabel gifLabel = new JLabel(gifIcon);
        gifLabel.setBounds(0, 0, 800, 500); // Ocupa todo el panel
        contentPanel.add(gifLabel); // Añadido al contentPanel

        // Etiqueta "Juego Completado" (encima del GIF)
        JLabel lblNewLabel = new JLabel("Juego Completado");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 36));
        lblNewLabel.setForeground(Color.WHITE); // O un color que contraste
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // Ajustar ancho (800 - 2*borde_horizontal si hubiera)
        // Como contentPanel tiene borde 5, usamos 800-10 = 790 aprox, pero como es null layout, 800 está bien.
        lblNewLabel.setBounds(0, 50, 800, 50);
        contentPanel.add(lblNewLabel); // Añadido al contentPanel


        // Botón "Continuar" (encima del GIF)
        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 18));
        btnContinuar.setBackground(new Color(128, 0, 128)); // Color Morado
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setBounds(300, 400, 200, 50);
        contentPanel.add(btnContinuar); // Añadido al contentPanel

        // Poner etiqueta y botón encima del GIF (Z-order)
        // El orden de add() en null layout no garantiza Z-order, pero intentamos
        contentPanel.setComponentZOrder(lblNewLabel, 0); // Índice 0 es el más alto (más al frente)
        contentPanel.setComponentZOrder(btnContinuar, 1); // Detrás de la etiqueta
        contentPanel.setComponentZOrder(gifLabel, 2);    // Detrás del botón

        // --- ActionListener Modificado para guardar y cerrar ---
        btnContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // --- Lógica para añadir puntos y guardar partida ---
                // Usamos las variables de instancia de la clase externa Game_2_Window
                if (Game_2_Window.this.cont != null && Game_2_Window.this.player != null) {

                    int pointsEarned = POINTS_EARNED_MATH; // Usar la constante definida
                    // Si quisieras puntos aleatorios:
                    // int pointsEarned = Game_2_Window.this.cont.RandomPoints();

                    try {
                        // 1. Guardar el registro de la partida
                        System.out.println("Game_2: Guardando partida: Juego=" + GAME_NAME + ", Score=" + pointsEarned);
                        Game_2_Window.this.cont.savePlayResult(Game_2_Window.this.player, GAME_NAME, pointsEarned);

                        // 2. Añadir los puntos al total del jugador (si la lógica lo requiere)
                        System.out.println("Game_2: Añadiendo " + pointsEarned + " puntos al total.");
                        Game_2_Window.this.cont.addPoints(Game_2_Window.this.player, pointsEarned);

                        // 3. Actualizar el objeto player local (útil si se reusa la instancia)
                        Game_2_Window.this.player.setPoints(Game_2_Window.this.player.getPoints() + pointsEarned);
                        System.out.println("Game_2: Puntos locales actualizados a: " + Game_2_Window.this.player.getPoints());

                    } catch (Exception ex) {
                         System.err.println("Error al guardar puntos/partida desde Game_2_Window: " + ex.getMessage());
                         // Informar al usuario del error
                         JOptionPane.showMessageDialog(Game_2_Window.this, // Padre es este JDialog
                               "Error al guardar la puntuación.\n" + ex.getMessage(),
                               "Error de Guardado", JOptionPane.ERROR_MESSAGE);
                         // Decide si cerrar la ventana igualmente o no
                         // dispose();
                         // return; // Podrías salir aquí para no cerrar si hubo error
                    }
                } else {
                     // Esto no debería pasar si el constructor valida
                     System.err.println("Error CRÍTICO: Controlador o jugador es null en Game_2_Window ActionListener.");
                }
                // --- Fin lógica ---

                // Cerrar esta ventana (JDialog) después del intento de guardado
                dispose();
            }
        });
    }

    // --- Método helper para cargar iconos (reutilizado) ---
    private ImageIcon loadIcon(String path) {
        try {
            // Usar el ClassLoader de la clase actual es más fiable
            java.net.URL imgURL = getClass().getResource(path);
            if (imgURL != null) {
                return new ImageIcon(imgURL);
            } else {
                System.err.println("No se pudo encontrar el recurso: " + path + " (relativo a " + getClass().getName() + ")");
                return createPlaceholderIcon(50); // Icono placeholder
            }
        } catch (Exception e) {
            System.err.println("Error al cargar icono: " + path + " - " + e.getMessage());
            return createPlaceholderIcon(50); // Icono placeholder
        }
    }

    // --- Método helper para crear icono placeholder (reutilizado) ---
    private ImageIcon createPlaceholderIcon(int size) {
        // Crea una imagen simple en caso de error
        java.awt.image.BufferedImage placeholder = new java.awt.image.BufferedImage(size, size, java.awt.image.BufferedImage.TYPE_INT_RGB);
        Graphics g = placeholder.createGraphics();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, size, size);
        g.setColor(Color.RED);
        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        g.drawString("?", size/2 - 4, size/2 + 5);
        g.dispose();
        return new ImageIcon(placeholder);
    }

} // Fin de la clase Game_2_Window