
package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel; // <<<--- Importar DefaultListModel
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel; // Importar JLabel si usas un título en el panel
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JOptionPane; // Para mensajes de error

import controlador.LoginControler;
import model.Player;
import model.Plays; // Importar Plays

// Implementar ActionListener para manejar eventos de botón
public class Stats_plays_Window extends JFrame implements ActionListener {

    private JPanel contentPane;
    private LoginControler cont;
    private Player player;

    // --- Estilos (Colores, Fuentes, Iconos) ---
    private Color darkPurple = new Color(48, 25, 52);
    private Color mediumPurple = new Color(102, 51, 153);
    private Color lightPurple = new Color(204, 153, 255);
    private Color textColor = new Color(240, 240, 240);
    private JButton btnGoB;
    // Carga segura del icono
    private ImageIcon HomeIcon = loadIcon("/images/Home.png");
    private Font titleFont = new Font("Segoe UI", Font.BOLD, 20); // Tamaño ajustado
    private Font listFont = new Font("Monospaced", Font.PLAIN, 14);
    private Font buttonFont = new Font("Segoe UI", Font.PLAIN, 16);
    // --- Fin Estilos ---

    /**
     * Constructor
     */
    public Stats_plays_Window(LoginControler cont, Player j) {
        // Validar entradas (opcional pero recomendado)
        if (cont == null || j == null) {
             throw new IllegalArgumentException("LoginControler y Player no pueden ser null.");
        }
        this.cont = cont;
        this.player = j;

        setTitle("Estadísticas de Partidas - " + player.getName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        // --- Panel principal con fondo degradado y BorderLayout ---
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, darkPurple, getWidth(), getHeight(), lightPurple.darker()); // Ajuste de color
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPane.setLayout(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        // --- Título (Opcional) ---
        JLabel lblWindowTitle = new JLabel("Historial de Partidas");
        lblWindowTitle.setFont(titleFont);
        lblWindowTitle.setForeground(textColor);
        lblWindowTitle.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblWindowTitle, BorderLayout.NORTH);


        // --- Lista de Estadísticas ---
        DefaultListModel<Plays> listModel = new DefaultListModel<>(); // <<<--- Usar DefaultListModel<Plays>
        JList<Plays> list = new JList<>(listModel); // <<<--- Crear JList con el Model
        list.setFont(listFont);
        list.setBackground(darkPurple.brighter()); // Fondo ligeramente más claro
        list.setForeground(textColor);
        list.setSelectionBackground(mediumPurple);
        list.setSelectionForeground(textColor);
        // list.setCellRenderer(...); // Podrías usar un CellRenderer personalizado para mejor formato

        // --- Cargar datos en el modelo ---
        try {
            ArrayList<Plays> playsData = cont.getPlays(j); // Llama al controlador
            if (playsData != null && !playsData.isEmpty()) {
                for (Plays play : playsData) {
                    listModel.addElement(play); // <<<--- Añadir objetos Plays al Model
                }
            } else {
                 // Mostrar mensaje en la lista si no hay datos
                 // Necesitarías cambiar el tipo del modelo a String o usar un renderer
                 // Por ahora, lo dejamos vacío o podrías añadir un JLabel aparte.
                 System.out.println("No se encontraron jugadas para " + player.getName());
                 // Podrías poner un mensaje en un JLabel en lugar de la lista aquí.
            }
        } catch (Exception e) {
            System.err.println("Error al obtener o mostrar jugadas: " + e.getMessage());
            e.printStackTrace(); // Para depuración
             // Mostrar mensaje de error al usuario
            JOptionPane.showMessageDialog(this,
                    "No se pudieron cargar las estadísticas.\nError: " + e.getMessage(),
                    "Error de Carga", JOptionPane.ERROR_MESSAGE);
             // Añadir mensaje de error al listModel si es de tipo String, o mostrar en otro lugar.
             // listModel.addElement("Error al cargar datos..."); // Solo si listModel fuera <String>
        }

        JScrollPane scrollPane = new JScrollPane(list); // Poner la lista en el scroll
        scrollPane.setBorder(BorderFactory.createLineBorder(lightPurple));

        // Añadir el scrollPane al CENTRO
        contentPane.add(scrollPane, BorderLayout.CENTER);
 

        // --- Panel Inferior y Botón ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        bottomPanel.setOpaque(false);
        btnGoB = createButton("Menú Principal", HomeIcon);
        btnGoB.addActionListener(this);
        bottomPanel.add(btnGoB);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);


        // --- Finalizar ---
        pack();
        setLocationRelativeTo(null); // Centrar
    }

    // --- Método createButton (sin cambios, revisa estilo si quieres) ---
    private JButton createButton(String text, ImageIcon icon) {
        JButton button = new JButton(text, icon);
        button.setFont(buttonFont);
        button.setForeground(textColor);
        button.setBackground(mediumPurple);
        button.setFocusPainted(false);
        button.setBorder(new CompoundBorder(
            new LineBorder(lightPurple, 1),
            new EmptyBorder(10, 20, 10, 20)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setIconTextGap(10);
        button.addMouseListener(new MouseAdapter() {
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
        });
        return button;
    }

    // --- Método loadIcon (reutilizado de Trophy_Window, necesita createPlaceholderIcon) ---
    private ImageIcon loadIcon(String path) {
        try {
            java.net.URL imgURL = getClass().getResource(path);
            if (imgURL != null) {
                return new ImageIcon(imgURL);
            } else {
                System.err.println("No se pudo encontrar el recurso: " + path);
                return createPlaceholderIcon(32); // Tamaño por defecto para placeholder
            }
        } catch (Exception e) {
            System.err.println("Error al cargar icono: " + path + " - " + e.getMessage());
            return createPlaceholderIcon(32);
        }
    }
    // --- Método createPlaceholderIcon (reutilizado de Trophy_Window) ---
     private ImageIcon createPlaceholderIcon(int size) {
        java.awt.image.BufferedImage placeholder = new java.awt.image.BufferedImage(size, size, java.awt.image.BufferedImage.TYPE_INT_RGB);
        Graphics g = placeholder.createGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, size, size);
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, size-1, size-1); // Borde simple
        g.drawString("?", size/2 - 4, size/2 + 5);
        g.dispose();
        return new ImageIcon(placeholder);
    }

    // --- Implementación de ActionListener ---
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGoB) {
            // Verificar que cont y player no sean null
             if (this.cont == null || this.player == null) {
                  System.err.println("Error: Intentando volver al menú sin controlador o jugador.");
                  // Quizás cerrar la ventana o mostrar error
                  this.dispose();
                  return;
             }
            // Crear y mostrar la ventana del menú principal
            Menu_Window menu = new Menu_Window(this.cont, this.player);
            menu.setVisible(true);
            // Cerrar esta ventana de estadísticas
            this.dispose();
        }
    }

    // --- Main opcional para pruebas (necesita adaptar cont.getPlays) ---
    /*
     public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            // --- Setup Mock Data ---
             // Crear un controlador mock o uno real si es posible
             LoginControler testCont = new LoginControler(); // Asumiendo que DBImplementation no falla al crear

             // Crear un jugador de prueba (asegúrate que existe en tu BD para que getPlays funcione)
             Player testPlayer = new Player("Alain", "123", 100); // Usa un jugador existente

             // Si no puedes conectar a BD, necesitarías un mock de LoginControler
             // que devuelva una lista dummy de Plays en getPlays()
             // --- End Mock Data ---

            try {
                Stats_plays_Window frame = new Stats_plays_Window(testCont, testPlayer);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
     }
    */

}
