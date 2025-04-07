





package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer; // Importar javax.swing.Timer
import javax.swing.border.EmptyBorder;

import controlador.LoginControler;
import model.Player;

// Implementamos ActionListener para el Timer
public class Game_1_Window extends JDialog implements ActionListener {

    private static final int GAME_DURATION_SECONDS = 60; // 1 minuto
    private static final int DOT_DIAMETER = 30;  //tamaño del zirculo donde se debe clickar para hacer puntos
    private static final int POINTS_HIT = 40;    //los puntos que se consiguen si le da al zirculo
    private static final int POINTS_MISS = -20; // puntos que se restan si clicka en un sitio de la ventana que no sea el circulo

    private JPanel contentPane;
    private GamePanel gamePanel; // Panel donde se juega
    private JLabel scoreLabel;
    private JLabel timeLabel;

    private LoginControler cont;
    private Player player;

    private Timer timer;	//control del tiempo
    private int timeLeft;	//cuanto tiempo le queda
    private int score;
    private Point dotPosition; // Posición actual del punto (esquina superior izquierda)
    private Random random;
    private boolean gameEnded = false; // Flag para evitar clics después de terminar

    /**
     * Constructor
     */
    public Game_1_Window(LoginControler cont, Player player) {
    	setModal(true);	//para que no pueda irse de la ventana hasta cerrarla o terminar el juego
        this.cont = cont;
        this.player = player;
        this.random = new Random();

        setTitle("Aim Game!");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Cerrar solo esta ventana
        setPreferredSize(new Dimension(800, 600)); // Tamaño de la ventana

        contentPane = new JPanel(new BorderLayout(0, 5)); // BorderLayout, poco espacio vertical
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10)); // borde de la ventana
        setContentPane(contentPane);  // para que se ponga lo mencionado antes

        // Panel Superior; donde hemos dejado espacio para poner los datos que queramos; en este caso son los especificados aqui:
        JPanel infoPanel = new JPanel(new BorderLayout()); 	//para ponerlo en la parte de arriba 
        scoreLabel = new JLabel("Score: 0", JLabel.LEFT);	//puntos que ha conseguido hasta ahora (se va actualizando x cada click)
        scoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));	//para cambiarle el "estilo"
        timeLabel = new JLabel("Time: " + GAME_DURATION_SECONDS, JLabel.RIGHT);		//el tiempo que le queda 
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));		//el mismo "estilo" que el score
        infoPanel.add(scoreLabel, BorderLayout.WEST);
        infoPanel.add(timeLabel, BorderLayout.EAST);		//para ponerlos en las posiciones donde queramos
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5)); // Padding
        contentPane.add(infoPanel, BorderLayout.NORTH); 	// añade el panel que contiene las etiquetas de puntuación y tiempo a la parte superior del panel principal de la ventana

        //Panel del Juego
        gamePanel = new GamePanel();
        contentPane.add(gamePanel, BorderLayout.CENTER);	//para que se ponga en el centro

        //Inicializar y empezar juego 
        startGame();

        //Listener para detener timer si se cierra la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopGame(); // Detener timer si se cierra con 'X'
            }
            @Override
            public void windowClosed(WindowEvent e) {
                stopGame(); // Asegurarse de detenerlo también al cerrar
            }
        });


        pack(); // ste método le dice a la ventana que ajuste su tamaño automáticamente para que todos los componentes que contiene quepan de forma "óptima"
        setLocationRelativeTo(null); //  Este método establece la posición inicial de la ventana en la pantalla del ordenador cuando se hace visible por primera vez.
    }

    //metodo para cuando empiece el juego
    private void startGame() {
        score = 0;  	//para que el score empiece en 0
        timeLeft = GAME_DURATION_SECONDS;	//Es una constante que almacena la duración total inicial del juego en segundos
        gameEnded = false;		// Flag para evitar clics después de terminar
        updateScoreLabel();		// Actualiza la etiqueta de puntuación.
        updateTimeLabel();		//Actualiza la etiqueta del tiempo restante.
        spawnNewDot(); 		//Genera una nueva posición aleatoria para el punto y redibuja.

        // Crear y empezar el temporizador (1000 ms = 1 segundo)
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        timer = new Timer(1000, this);		//Esta línea crea un temporizador de Swing que se configurará para ejecutar una acción cada segundo (1000 ms). La acción que se ejecutará es el método actionPerformed que tienes implementado dentro de la misma clase (Game_1_Window), porque le estás pasando this como el objeto que escuchará los eventos del temporizador.	
        timer.start();		//para empezar el temporizador
        System.out.println("Juego iniciado. Timer corriendo.");
    }

    /**
     * Detiene el temporizador del juego.
     */
    private void stopGame() {
         if (timer != null && timer.isRunning()) {
             timer.stop();
             System.out.println("Timer detenido.");
         }
         gameEnded = true; // Marcar como terminado para no procesar más clics
    }

    /**
     * Genera una nueva posición aleatoria para el punto y redibuja.
     */
    private void spawnNewDot() {
        // Asegurarse que gamePanel tiene tamaño antes de calcular
        if (gamePanel.getWidth() <= DOT_DIAMETER || gamePanel.getHeight() <= DOT_DIAMETER) {
            // Si el panel es muy pequeño, ponerlo en la esquina (o esperar a que tenga tamaño)
             dotPosition = new Point(0, 0);
        } else {
            int x = random.nextInt(gamePanel.getWidth() - DOT_DIAMETER);
            int y = random.nextInt(gamePanel.getHeight() - DOT_DIAMETER);
            dotPosition = new Point(x, y);		//operacion para generar el zirculo aleatoriamente sin que se salga de la ventana
        }
        gamePanel.repaint(); // Pedir redibujado
    }

    /**
     * Actualiza la etiqueta de puntuación.
     */
    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + score);
    }

    /**
     * Actualiza la etiqueta del tiempo restante.
     */
    private void updateTimeLabel() {
        timeLabel.setText("Time: " + timeLeft);
    }

    /**
     * Lógica que se ejecuta cuando el tiempo se acaba.
     */
    private void endGame() {
        stopGame(); // Detener timer y marcar como terminado

        // --- Guardar Resultado de la Partida (En tabla PLAYS) ---
        if (cont != null && player != null && score >= 0) { // Guardar si score >= 0
             try {
                 String gameName = "AIM"; // Nombre del juego actual 
                 System.out.println("Intentando guardar partida: Jugador=" + player.getName() + ", Juego=" + gameName + ", Score=" + score);
                 cont.savePlayResult(this.player, gameName, this.score); // Guarda en PLAYS
             } catch (Exception ex) {
                  System.err.println("Error al llamar a savePlayResult: " + ex.getMessage());
                  // Considera informar al usuario si el guardado del registro falla
             }
        }




        // --- Actualizar Puntos Totales del Jugador (En tabla PLAYER) ---
        if (cont != null && player != null && score > 0) { // Solo añadir si el score es positivo
             try {
                 System.out.println("Añadiendo " + score + " puntos al total del jugador " + player.getName());
                 cont.addPoints(player, score); 
                 player.setPoints(player.getPoints() + score);
                 System.out.println("Puntos locales actualizados a: " + player.getPoints());

             } catch (Exception ex) {
                 System.err.println("Error al añadir puntos finales: " + ex.getMessage());
                 // Mostrar un error al usuario sería útil aquí
                 JOptionPane.showMessageDialog(this,
                       "Ocurrió un error al guardar tu puntuación total.",
                       "Error al Guardar Puntos", JOptionPane.ERROR_MESSAGE);
             }
        }
 


        // Mostrar mensaje final (sin cambios)
        JOptionPane.showMessageDialog(this,
                "¡Tiempo Terminado!\nPuntuación Final: " + score,
                "Fin del Juego",
                JOptionPane.INFORMATION_MESSAGE);

        // Cerrar la ventana del juego después de que el usuario pulse OK
        dispose();
        
    }

    /**
     * Manejador de eventos para el Timer.
     * Se llama cada segundo.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameEnded) return; // No hacer nada si el juego ya terminó

        if (e.getSource() == timer) {
            timeLeft --;
            updateTimeLabel();
            if (timeLeft <= 0) {
                endGame(); // El tiempo se acabó
            }
        }
    }

    // --- Clase interna para el Panel del Juego ---
    private class GamePanel extends JPanel {

        public GamePanel() {
            setBackground(Color.DARK_GRAY); // Fondo del área de juego
            // Listener para clics del ratón
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                     if (gameEnded || dotPosition == null) return; // No hacer nada si terminó o no hay punto

                    // Crear un rectángulo temporal para la posición actual del punto
                    Rectangle dotBounds = new Rectangle(dotPosition.x, dotPosition.y, DOT_DIAMETER, DOT_DIAMETER);

                    // Comprobar si el clic fue dentro del rectángulo del punto
                    if (dotBounds.contains(e.getPoint())) {
                        // --- HIT ---
                        score += POINTS_HIT;	//coje el valor actual y le suma los puntos obtenidos (40) por acertar
                        System.out.println("HIT! Score: " + score); // Debug
                        spawnNewDot(); // Mover el punto a una nueva posición
                    } else {
                        // --- MISS ---
                        score += POINTS_MISS; // Sumar puntos negativos
                        if (score < 0) {
                            score = 0; // Evitar puntuación negativa
                        }
                        System.out.println("MISS! Score: " + score); // Debug
                    }
                    updateScoreLabel(); // Actualizar la etiqueta de puntuación
                }
            });
        }

        /**
         * Método para dibujar el contenido del panel (fondo y punto).
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // Dibuja el fondo (DARK_GRAY)

            // Dibujar el punto si existe una posición
            if (dotPosition != null) {
                Graphics2D g2d = (Graphics2D) g.create(); // Crear copia para no afectar otros dibujos
                 // Antialiasing para bordes suaves
                 g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.CYAN); // Color del punto (azul claro/cyan)
                g2d.fillOval(dotPosition.x, dotPosition.y, DOT_DIAMETER, DOT_DIAMETER);
                g2d.dispose(); // Liberar recursos gráficos de la copia
            }
        }
    } // --- Fin Clase GamePanel ---

} // --- Fin Clase Game_1_Window ---