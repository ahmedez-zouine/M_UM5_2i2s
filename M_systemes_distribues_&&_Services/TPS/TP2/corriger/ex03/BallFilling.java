import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BallFilling extends JFrame {
    private JPanel drawingPanel;
    private JButton startButton;
    private static final int BALL_SIZE = 20;
    private static final int PANEL_WIDTH = 400;
    private static final int PANEL_HEIGHT = 400;
    private java.util.List<Point> balls;
    private boolean isRunning;
    private int currentRow;
    private int currentCol;
    
    public BallFilling() {
        
        setTitle("Remplissage de Balles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        balls = new java.util.ArrayList<>();
        isRunning = false;
        currentRow = 0;
        currentCol = 0;
        
        // Création du panel de dessin
        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Point p : balls) {
                    g.setColor(Color.BLACK);
                    g.fillOval(p.x, p.y, BALL_SIZE, BALL_SIZE);
                }
            }
        };
        drawingPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        drawingPanel.setBackground(Color.WHITE);
        
        // Création du bouton Start
        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isRunning) {
                    isRunning = true;
                    startButton.setEnabled(false);
                    startFilling();
                }
            }
        });
        
        // Layout
        setLayout(new BorderLayout());
        add(drawingPanel, BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private void startFilling() {
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentRow * BALL_SIZE < PANEL_HEIGHT) {
                    if (currentCol * BALL_SIZE < PANEL_WIDTH) {
                        // Ajouter une nouvelle balle
                        balls.add(new Point(currentCol * BALL_SIZE, currentRow * BALL_SIZE));
                        currentCol++;
                    } else {
                        // Passer à la ligne suivante
                        currentCol = 0;
                        currentRow++;
                    }
                    drawingPanel.repaint();
                } else {
                    // Arrêter le timer quand le remplissage est terminé
                    ((Timer)e.getSource()).stop();
                    isRunning = false;
                    startButton.setEnabled(true);
                }
            }
        });
        timer.start();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BallFilling().setVisible(true);
            }
        });
    }
}