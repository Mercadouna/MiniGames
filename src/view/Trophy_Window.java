package view;

// --- Importaciones existentes ---
import java.awt.BorderLayout;
import java.awt.Color; // Importar Color
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap; // Para asociar botones con datos
import java.util.Map;    // Para asociar botones con datos

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane; // Para mostrar mensajes
import javax.swing.JPanel;
import javax.swing.SwingConstants; // Para alinear texto
import javax.swing.border.EmptyBorder;

import controlador.LoginControler;
import model.InsufficientPointsException; // Importar excepción
import model.Player;
import view.Menu_Window;

// --- Clase Trophy_Window ---
public class Trophy_Window extends JDialog implements ActionListener {

    // --- Componentes UI existentes ---
    private final JPanel contentPanel = new JPanel();
    private JButton btnMenu;
    private JButton btnBuy_1, btnBuy_2, btnBuy_3, btnBuy_4, btnBuy_5;
    private JLabel lblImage_1a, lblImage_1b, lblImage_2a, lblImage_2b, lblImage_3a, lblImage_3b, lblImage_4a, lblImage_4b, lblImage_5a, lblImage_5b;

    // --- Nuevos Componentes UI para estado ---
    private JLabel lblStatus_1, lblStatus_2, lblStatus_3, lblStatus_4, lblStatus_5;
    private JLabel lblPlayerPoints; // Para mostrar los puntos del jugador

    // --- Referencias y datos ---
    private LoginControler loginControler;
    private Player player;

    // Estructura para asociar botones con datos de trofeos (Nombre y Precio)
    // Idealmente, cargarías esto desde la BD, pero para simplificar lo hardcodeamos aquí
    // ¡¡ASEGÚRATE QUE LOS NOMBRES Y PRECIOS COINCIDEN CON LA BD!!
    private static class TrophyData {
        String name;
        int price;
        JLabel lblStatus;
        JLabel lblImageA;
        JLabel lblImageB;
        JButton btnBuy;

        TrophyData(String name, int price, JLabel lblStatus, JLabel lblImageA, JLabel lblImageB, JButton btnBuy) {
            this.name = name;
            this.price = price;
            this.lblStatus = lblStatus;
            this.lblImageA = lblImageA;
            this.lblImageB = lblImageB;
            this.btnBuy = btnBuy;
        }
    }
    private Map<JButton, TrophyData> trophyMap = new HashMap<>();


    // --- Constructor ---
    public Trophy_Window(Player player, LoginControler loginControler) {
        this.player = player;
        this.loginControler = loginControler;
        setTitle("Sala de Trofeos - " + player.getName());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setBounds(100, 100, 836, 550); // Aumentar altura para puntos y estados
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        // --- Inicializar Componentes UI (incluyendo los nuevos de estado y puntos) ---

        // Etiqueta para mostrar puntos del jugador
        lblPlayerPoints = new JLabel("Puntos: " + player.getPoints());
        lblPlayerPoints.setBounds(10, 10, 200, 20); // Posición superior izquierda
        contentPanel.add(lblPlayerPoints);

        // --- Trofeo 1 ---
        lblImage_1a = createLabel("/images/question.png", 73, 60); // Ajustar Y para dejar espacio a puntos
        contentPanel.add(lblImage_1a);
        lblImage_1b = createLabel("/images/trophy.png", 73, 60);
        lblImage_1b.setVisible(false);
        contentPanel.add(lblImage_1b);
        btnBuy_1 = createButton("Comprar (100 P)", 62, 185); // Ajustar Y, mostrar precio
        contentPanel.add(btnBuy_1);
        lblStatus_1 = createStatusLabel(62, 210); // Posición debajo del botón
        contentPanel.add(lblStatus_1);
        trophyMap.put(btnBuy_1, new TrophyData("STARTING TROPHYE", 100, lblStatus_1, lblImage_1a, lblImage_1b, btnBuy_1));

        // --- Trofeo 2 ---
        lblImage_2a = createLabel("/images/question.png", 305, 60);
        contentPanel.add(lblImage_2a);
        lblImage_2b = createLabel("/images/trophy.png", 305, 60);
        lblImage_2b.setVisible(false);
        contentPanel.add(lblImage_2b);
        btnBuy_2 = createButton("Comprar (300 P)", 300, 185); // Ajustar Y
        contentPanel.add(btnBuy_2);
        lblStatus_2 = createStatusLabel(300, 210);
        contentPanel.add(lblStatus_2);
        trophyMap.put(btnBuy_2, new TrophyData("RARE TROPHYE", 300, lblStatus_2, lblImage_2a, lblImage_2b, btnBuy_2));

        // --- Trofeo 3 ---
        lblImage_3a = createLabel("/images/question.png", 549, 60);
        contentPanel.add(lblImage_3a);
        lblImage_3b = createLabel("/images/trophy.png", 549, 60);
        lblImage_3b.setVisible(false);
        contentPanel.add(lblImage_3b);
        btnBuy_3 = createButton("Comprar (800 P)", 540, 185); // Ajustar Y
        contentPanel.add(btnBuy_3);
        lblStatus_3 = createStatusLabel(540, 210);
        contentPanel.add(lblStatus_3);
        trophyMap.put(btnBuy_3, new TrophyData("EPIC TROPHYE", 800, lblStatus_3, lblImage_3a, lblImage_3b, btnBuy_3));

         // --- Trofeo 4 ---
        // Cuidado con el solapamiento de coordenadas que tenías antes
        lblImage_4a = createLabel("/images/question.png", 218, 260); // Ajustar Y
        contentPanel.add(lblImage_4a);
        lblImage_4b = createLabel("/images/trophy.png", 218, 260);
        lblImage_4b.setVisible(false);
        contentPanel.add(lblImage_4b);
        btnBuy_4 = createButton("Comprar (1600 P)", 200, 385); // Ajustar Y
        contentPanel.add(btnBuy_4);
        lblStatus_4 = createStatusLabel(200, 410);
        contentPanel.add(lblStatus_4);
        trophyMap.put(btnBuy_4, new TrophyData("MITHYC TROPHYE", 1600, lblStatus_4, lblImage_4a, lblImage_4b, btnBuy_4)); // Revisa nombre/precio MITHYC

         // --- Trofeo 5 ---
        lblImage_5a = createLabel("/images/question.png", 463, 260); // Ajustar Y
        contentPanel.add(lblImage_5a);
        lblImage_5b = createLabel("/images/trophy.png", 463, 260);
        lblImage_5b.setVisible(false);
        contentPanel.add(lblImage_5b);
        btnBuy_5 = createButton("Comprar (5000 P)", 450, 385); // Ajustar Y
        contentPanel.add(btnBuy_5);
        lblStatus_5 = createStatusLabel(450, 410);
        contentPanel.add(lblStatus_5);
        trophyMap.put(btnBuy_5, new TrophyData("LEGENDARY TROPHY", 5000, lblStatus_5, lblImage_5a, lblImage_5b, btnBuy_5)); // Revisa nombre/precio LEGENDARY


        // --- Botón Menú ---
        btnMenu = new JButton("Menu");
        btnMenu.setBounds(708, 480, 85, 21); // Ajustar Y
        contentPanel.add(btnMenu);
        btnMenu.addActionListener(this);

        // --- Añadir Listeners a botones de compra ---
        btnBuy_1.addActionListener(this);
        btnBuy_2.addActionListener(this);
        btnBuy_3.addActionListener(this);
        btnBuy_4.addActionListener(this);
        btnBuy_5.addActionListener(this);

        // Carga inicial del estado de los trofeos
        loadTrophiesStatus();
    }

    // --- Métodos Helper para crear componentes (opcional, para limpiar constructor) ---
    private JLabel createLabel(String imagePath, int x, int y) {
        JLabel label = new JLabel("");
        try {
             label.setIcon(new ImageIcon(Trophy_Window.class.getResource(imagePath)));
        } catch (NullPointerException e) {
            System.err.println("Error cargando imagen: " + imagePath);
             label.setText("Error Img"); // Texto alternativo si falla la carga
        }
        label.setBounds(x, y, 94, 93); // Tamaño fijo
        return label;
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        // Ajustar ancho según necesidad o dejar que Swing lo calcule
        button.setBounds(x, y, 120, 23); // Ancho ajustado para texto más largo
        return button;
    }

    private JLabel createStatusLabel(int x, int y) {
        JLabel label = new JLabel("");
        label.setForeground(new Color(0, 128, 0)); // Verde oscuro
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(x, y, 120, 15); // Ancho igual al botón
        return label;
    }

    // --- Método para actualizar estado inicial de trofeos ---
    private void loadTrophiesStatus() {
        ArrayList<String> boughtTrophies;
        try {
             // Usar el método del controlador que obtiene el ID internamente es más seguro
            boughtTrophies = loginControler.getBoughtTrophies(player);
        } catch (Exception e) { // Captura genérica si getBoughtTrophies puede fallar
             System.err.println("Error al cargar trofeos comprados: " + e.getMessage());
             boughtTrophies = new ArrayList<>(); // Lista vacía para evitar NullPointerException
        }

        // Iterar sobre los datos de trofeos que definimos
        for (TrophyData data : trophyMap.values()) {
            if (boughtTrophies.contains(data.name)) {
                // El jugador ya tiene este trofeo
                data.lblImageA.setVisible(false);
                data.lblImageB.setVisible(true);
                data.lblStatus.setText("Desbloqueado");
                data.btnBuy.setEnabled(false); // Desactivar botón si ya lo tiene
                data.btnBuy.setText("Comprado"); // Cambiar texto botón (opcional)
            } else {
                // El jugador no tiene este trofeo
                data.lblImageA.setVisible(true);
                data.lblImageB.setVisible(false);
                data.lblStatus.setText(""); // Sin texto de estado
                data.btnBuy.setEnabled(true); // Asegurar que el botón esté activo
                 // Asegurar texto original del botón (con precio)
                 data.btnBuy.setText("Comprar (" + data.price + " P)");
            }
        }
         // Actualizar siempre los puntos mostrados
         updatePointsDisplay();
    }

    // --- Método para actualizar la etiqueta de puntos ---
     private void updatePointsDisplay() {
         lblPlayerPoints.setText("Puntos: " + player.getPoints());
     }


    // --- ActionListener ---
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == btnMenu) {
            // Volver al menú (ya corregido)
            Menu_Window mw = new Menu_Window(this.loginControler, this.player);
            mw.setVisible(true);
            this.dispose();
        } else if (trophyMap.containsKey(source)) {
             // Se ha pulsado uno de los botones de compra
             TrophyData data = trophyMap.get(source);
             handleBuyAttempt(data); // Llamar al método helper de compra
        }
    }

    // --- Método Helper para gestionar el intento de compra ---
    private void handleBuyAttempt(TrophyData trophyData) {
        // Confirmación opcional
        int confirmation = JOptionPane.showConfirmDialog(this,
                "¿Comprar '" + trophyData.name + "' por " + trophyData.price + " puntos?",
                "Confirmar Compra",
                JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                // Intentar comprar a través del controlador
                boolean success = loginControler.buyTrophy(this.player, trophyData.name, trophyData.price);

                if (success) {
                    // Compra exitosa en la BD, actualizar UI y estado local
                    this.player.setPoints(this.player.getPoints() - trophyData.price); // Actualizar puntos locales
                    updatePointsDisplay(); // Actualizar etiqueta de puntos

                    trophyData.lblImageA.setVisible(false);
                    trophyData.lblImageB.setVisible(true);
                    trophyData.lblStatus.setText("Desbloqueado");
                    trophyData.btnBuy.setEnabled(false);
                    trophyData.btnBuy.setText("Comprado");

                    JOptionPane.showMessageDialog(this,
                            "¡'" + trophyData.name + "' comprado con éxito!",
                            "Compra Realizada", JOptionPane.INFORMATION_MESSAGE);

                } else {
                     // La compra no tuvo éxito por una razón distinta a puntos insuficientes
                     // (ej: ya lo tenía, error inesperado en BD sin excepción SQL)
                     // El método buyTrophy en el DAO ya podría haber mostrado un mensaje en consola.
                     // Podríamos necesitar actualizar la UI si la razón fue "ya lo tenía".
                     loadTrophiesStatus(); // Recargar estado por si acaso
                     JOptionPane.showMessageDialog(this,
                            "No se pudo completar la compra.\n(Puede que ya tuvieras el trofeo o hubo un error)",
                            "Compra Fallida", JOptionPane.WARNING_MESSAGE);
                }

            } catch (InsufficientPointsException ipe) {
                // Capturar la excepción específica de puntos insuficientes
                JOptionPane.showMessageDialog(this,
                        ipe.getMessage(), // Usar el mensaje de la excepción
                        "Puntos Insuficientes", JOptionPane.WARNING_MESSAGE);

            } catch (Exception ex) {
                // Capturar cualquier otro error inesperado (ej: SQLException no capturada antes)
                System.err.println("Error inesperado durante la compra: " + ex.getMessage());
                 // ex.printStackTrace(); // Para depuración
                JOptionPane.showMessageDialog(this,
                        "Ocurrió un error inesperado al intentar comprar.\nConsulta los logs para más detalles.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } // Fin if (confirmation == YES_OPTION)
    }

} // Fin clase Trophy_Window