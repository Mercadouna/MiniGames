package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControler;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class Game_1_Window extends JDialog {

    private final JPanel contentPanel = new JPanel();

    public Game_1_Window(LoginControler cont) {
    	setModal(true);
        setBounds(100, 100, 800, 500);
        getContentPane().setLayout(null);
        contentPanel.setBounds(0, 0, 800, 500);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel);
        contentPanel.setLayout(null);

        // Cargar el GIF desde el paquete "images"
        ImageIcon gifIcon = new ImageIcon(getClass().getResource("/images/aimlab.gif"));
        JLabel gifLabel = new JLabel(gifIcon);
        gifLabel.setBounds(0, 0, 800, 500);
        contentPanel.add(gifLabel);

        // Etiqueta de "Juego Completado"
        JLabel lblNewLabel = new JLabel("Juego Completado");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 36));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 50, 800, 50);
        contentPanel.add(lblNewLabel);

        // Botón "Continuar"
        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 18));
        btnContinuar.setBackground(new Color(128, 0, 128));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setBounds(300, 400, 200, 50);
        contentPanel.add(btnContinuar);

        // Agregar ActionListener para cerrar la ventana
        btnContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana
            }
        });
    }
}