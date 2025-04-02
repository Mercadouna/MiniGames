package view;

// --- Importaciones (sin cambios) ---
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component; // Necesario para alignmentX
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.LoginControler;
import model.InsufficientPointsException;
import model.Player;
// --- Fin Importaciones ---

public class Trophy_Window extends JDialog implements ActionListener {

    // --- Colores y Fuentes (sin cambios) ---
    private Color darkPurple = new Color(48, 25, 52);
    private Color mediumPurple = new Color(102, 51, 153);
    private Color lightPurple = new Color(204, 153, 255);
    private Color textColor = new Color(240, 240, 240);
    private Color successColor = new Color(34, 139, 34); // Verde Bosque
    private Color panelBgColor = new Color(255, 255, 255, 50);

    private Font titleFont = new Font("Segoe UI", Font.BOLD, 28);
    private Font pointsFont = new Font("Segoe UI", Font.BOLD, 16);
    private Font trophyNameFont = new Font("Segoe UI", Font.BOLD, 14);
    private Font trophyInfoFont = new Font("Segoe UI", Font.PLAIN, 12);
    private Font buttonFont = new Font("Segoe UI", Font.BOLD, 12);
    private Font statusFont = new Font("Segoe UI", Font.BOLD, 12);
    // --- Fin Colores y Fuentes ---

    // --- Componentes UI ---
    private JPanel contentPane;
    private JLabel lblPlayerPoints;
    private JPanel trophiesPanel; // << --- Hacer variable de instancia

    // --- Referencias y datos ---
    private LoginControler loginControler;
    private Player player;
    private ArrayList<TrophyUIData> trophyList = new ArrayList<>();

    // Clase interna TrophyUIData (sin cambios)
    private static class TrophyUIData {
        String name;
        int price;
        String imagePath;
        String questionImagePath = "/images/question.png";
        JLabel lblImage;
        JLabel lblName;
        JLabel lblPrice;
        JLabel lblStatus;
        JButton btnBuy;

        TrophyUIData(String name, int price, String imagePath) {
            this.name = name;
            this.price = price;
            this.imagePath = imagePath;
        }
    }
    // --- Fin Referencias y datos ---


    // --- Constructor ---
    public Trophy_Window(Player player, LoginControler loginControler) {
        this.player = player;
        this.loginControler = loginControler;
        setTitle("Sala de Trofeos - " + player.getName());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setPreferredSize(new Dimension(850, 650));

        // --- Inicializar datos de trofeos (sin cambios) ---
        trophyList.add(new TrophyUIData("STARTING TROPHYE", 100, "/images/trophy.png"));
        trophyList.add(new TrophyUIData("RARE TROPHYE", 300, "/images/aim.png"));
        trophyList.add(new TrophyUIData("EPIC TROPHYE", 800, "/images/math.png"));
        trophyList.add(new TrophyUIData("MITHYC TROPHYE", 1600, "/images/record.png"));
        trophyList.add(new TrophyUIData("LEGENDARY TROPHY", 5000, "/images/user.png"));

        // --- Panel Principal (sin cambios) ---
        contentPane = new JPanel(new BorderLayout(20, 20)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, darkPurple, 0, getHeight(), mediumPurple);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        // --- Panel Superior (sin cambios) ---
        JPanel topPanel = new JPanel(new BorderLayout(10, 0));
        topPanel.setOpaque(false);
        JLabel lblTitle = new JLabel("SALA DE TROFEOS");
        lblTitle.setFont(titleFont);
        lblTitle.setForeground(textColor);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(lblTitle, BorderLayout.CENTER);
        lblPlayerPoints = new JLabel("Puntos: " + player.getPoints());
        lblPlayerPoints.setFont(pointsFont);
        lblPlayerPoints.setForeground(textColor);
        lblPlayerPoints.setHorizontalAlignment(SwingConstants.RIGHT);
        topPanel.add(lblPlayerPoints, BorderLayout.EAST);
        contentPane.add(topPanel, BorderLayout.NORTH);

        // --- Panel Central (Grid de Trofeos) ---
        // Asignar a la variable de instancia this.trophiesPanel
        this.trophiesPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        this.trophiesPanel.setOpaque(false);

        for (TrophyUIData data : trophyList) {
            JPanel trophyCard = createTrophyCard(data);
            this.trophiesPanel.add(trophyCard); // Añadir al panel de instancia
        }
        contentPane.add(this.trophiesPanel, BorderLayout.CENTER); // Añadir el panel de instancia

        // --- Panel Inferior (sin cambios) ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        JButton btnMenu = createStyledButton("Volver al Menú");
        btnMenu.setActionCommand("MENU");
        btnMenu.addActionListener(this);
        bottomPanel.add(btnMenu);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        // --- Carga inicial estado y empaquetar ---
        loadTrophiesStatus();
        pack();
        setLocationRelativeTo(null);
    }

    // --- Método createTrophyCard (sin cambios) ---
    private JPanel createTrophyCard(TrophyUIData data) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS)); // Layout vertical
        card.setBackground(panelBgColor);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(lightPurple, 1),
                new EmptyBorder(15, 15, 15, 15) // Padding interno
        ));
        card.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar tarjeta si el layout lo permite

        // Imagen (inicialmente interrogante)
        data.lblImage = new JLabel();
        data.lblImage.setIcon(loadIcon(data.questionImagePath, 90)); // Cargar icono interrogante
        data.lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        data.lblImage.setBorder(new EmptyBorder(0, 0, 10, 0)); // Espacio debajo imagen
        card.add(data.lblImage);

        // Nombre
        data.lblName = new JLabel(data.name);
        data.lblName.setFont(trophyNameFont);
        data.lblName.setForeground(textColor);
        data.lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(data.lblName);

        // Precio
        data.lblPrice = new JLabel(data.price + " Puntos");
        data.lblPrice.setFont(trophyInfoFont);
        data.lblPrice.setForeground(lightPurple); // Color diferente para precio
        data.lblPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(data.lblPrice);

        // Estado (inicialmente vacío)
        data.lblStatus = new JLabel(" "); // Espacio para mantener altura
        data.lblStatus.setFont(statusFont);
        data.lblStatus.setForeground(successColor);
        data.lblStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(data.lblStatus);
        card.add(Box.createVerticalStrut(10)); // Espacio antes del botón

        // Botón Comprar
        data.btnBuy = createStyledButton("Comprar");
        data.btnBuy.setActionCommand(data.name); // Usar nombre como comando
        data.btnBuy.addActionListener(this);
        data.btnBuy.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(data.btnBuy);

        return card;
    }

    // --- Método createStyledButton (sin cambios) ---
     private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(buttonFont);
        button.setForeground(textColor);
        button.setBackground(mediumPurple);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(darkPurple, 1),
            new EmptyBorder(8, 15, 8, 15)
        ));
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

    // --- Método loadIcon (sin cambios) ---
    private ImageIcon loadIcon(String path, int size) {
        try {
            java.net.URL imgURL = Trophy_Window.class.getResource(path);
            if (imgURL != null) {
                return new ImageIcon(imgURL);
            } else {
                System.err.println("No se pudo encontrar el recurso: " + path);
                return createPlaceholderIcon(size);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar icono: " + path + " - " + e.getMessage());
            return createPlaceholderIcon(size);
        }
    }

    // --- Método createPlaceholderIcon (sin cambios) ---
     private ImageIcon createPlaceholderIcon(int size) {
        java.awt.image.BufferedImage placeholder = new java.awt.image.BufferedImage(size, size, java.awt.image.BufferedImage.TYPE_INT_RGB);
        Graphics g = placeholder.createGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, size, size);
        g.setColor(Color.WHITE);
        g.drawString("?", size/2 - 4, size/2 + 4);
        g.dispose();
        return new ImageIcon(placeholder);
    }

    // --- Método loadTrophiesStatus ---
    private void loadTrophiesStatus() {
        ArrayList<String> boughtTrophies;
        try {
            boughtTrophies = loginControler.getBoughtTrophies(player);
        } catch (Exception e) {
            System.err.println("Error al cargar trofeos comprados: " + e.getMessage());
            boughtTrophies = new ArrayList<>();
        }

        // Bucle para actualizar cada tarjeta
        for (TrophyUIData data : trophyList) {
            boolean isBought = boughtTrophies.contains(data.name);

            if (isBought) {
                data.lblImage.setIcon(loadIcon(data.imagePath, 90));
                data.lblStatus.setText("Desbloqueado");
                data.lblStatus.setForeground(successColor);
                data.btnBuy.setEnabled(false);
                data.btnBuy.setText("Comprado");
                data.lblPrice.setVisible(false);
            } else {
                data.lblImage.setIcon(loadIcon(data.questionImagePath, 90));
                data.lblStatus.setText(" ");
                data.btnBuy.setEnabled(true);
                data.btnBuy.setText("Comprar");
                data.lblPrice.setVisible(true);
            }
        } // --- Fin del bucle for ---

        // --- !! NUEVAS LÍNEAS !! ---
        // Después de actualizar todos los componentes, indicar a Swing
        // que recalcule el layout y redibuje el panel contenedor.
        if (this.trophiesPanel != null) { // Comprobación de seguridad
            this.trophiesPanel.revalidate(); // Recalcular layout
            this.trophiesPanel.repaint(); // Redibujar el panel y sus hijos
        }
        // --- !! FIN NUEVAS LÍNEAS !! ---

        updatePointsDisplay(); // Actualizar puntos
    }

    // --- Método updatePointsDisplay (sin cambios) ---
    private void updatePointsDisplay() {
        lblPlayerPoints.setText("Puntos: " + player.getPoints());
    }

    // --- ActionListener (sin cambios) ---
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("MENU".equals(command)) {
            Menu_Window mw = new Menu_Window(this.loginControler, this.player);
            mw.setVisible(true);
            this.dispose();
        } else {
            TrophyUIData targetTrophy = null;
            for (TrophyUIData data : trophyList) {
                if (data.name.equals(command)) {
                    targetTrophy = data;
                    break;
                }
            }
            if (targetTrophy != null) {
                handleBuyAttempt(targetTrophy);
            } else {
                 System.err.println("Comando de acción desconocido: " + command);
            }
        }
    }

    // --- Método handleBuyAttempt (sin cambios) ---
    private void handleBuyAttempt(TrophyUIData trophyData) {
        int confirmation = JOptionPane.showConfirmDialog(this,
                "¿Deseas comprar '" + trophyData.name + "' por " + trophyData.price + " puntos?",
                "Confirmar Compra",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                boolean success = loginControler.buyTrophy(this.player, trophyData.name, trophyData.price);

                if (success) {
                    this.player.setPoints(this.player.getPoints() - trophyData.price);
                    // Ya no llamamos a updatePointsDisplay() aquí, loadTrophiesStatus lo hará
                    loadTrophiesStatus(); // Recargar TODO el estado visual
                    JOptionPane.showMessageDialog(this,
                            "¡Has comprado '" + trophyData.name + "'!",
                            "Compra Exitosa", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    loadTrophiesStatus(); // Recargar por si acaso (ej: ya lo tenía)
                    JOptionPane.showMessageDialog(this,
                            "No se pudo completar la compra.\n(Es posible que ya tuvieras el trofeo).",
                            "Compra Fallida", JOptionPane.WARNING_MESSAGE);
                }
            } catch (InsufficientPointsException ipe) {
                JOptionPane.showMessageDialog(this,
                        ipe.getMessage(),
                        "Puntos Insuficientes", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                System.err.println("Error inesperado durante la compra: " + ex.getMessage());
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Ocurrió un error inesperado al intentar comprar.\nRevisa la consola para más detalles.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
} // Fin clase Trophy_Window