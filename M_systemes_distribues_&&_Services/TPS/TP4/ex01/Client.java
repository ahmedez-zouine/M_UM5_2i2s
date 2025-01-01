import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class Client 
{
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private GameBoard gameBoard;
    private String mark;
    private volatile boolean myTurn;
    private volatile boolean gameStarted;
    
    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            gameBoard = new GameBoard(this);
            gameStarted = false;
            
            // Start message receiving thread
            new Thread(this::receiveMessages).start();
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error connecting to server: " + e.getMessage());
            System.exit(1);
        }
    }
    
    private void receiveMessages() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                String[] parts = message.split("\\|");
                switch (parts[0]) 
                {
                    case "WAIT":
                        gameBoard.updateStatus(parts[1]);
                        break;
                    case "START":
                        mark = parts[1];
                        myTurn = mark.equals("X");
                        gameStarted = true;
                        gameBoard.updateStatus(myTurn ? "Your turn" : "Opponent's turn");
                        break;
                    case "MOVE":
                        int position = Integer.parseInt(parts[1]);
                        String playerMark = parts[2];
                        gameBoard.updateButton(position, playerMark);
                        break;
                    case "TURN":
                        myTurn = parts[1].equals(mark);
                        gameBoard.updateStatus(myTurn ? "Your turn" : "Opponent's turn");
                        break;
                    case "WIN":
                        String winner = parts[1];
                        gameBoard.showMessage(winner.equals(mark) ? "You win!" : "You lose!");
                        gameBoard.disableButtons();
                        gameStarted = false;
                        break;
                    case "DRAW":
                        gameBoard.showMessage("Game is a draw!");
                        gameBoard.disableButtons();
                        gameStarted = false;
                        break;
                }
            }
        } catch (IOException e) {
            gameBoard.showMessage("Connection lost!");
            gameBoard.disableButtons();
        }
    }

    public void sendMove(int position) {
        if (gameStarted && myTurn) {
            out.println(position);
        }
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Client("localhost", 1234);
        });
    }
}

class GameBoard extends JFrame 
{
    private JButton[] buttons;
    private JLabel statusLabel;
    private Client client;
    
    public GameBoard(Client client) 
    {
        this.client = client;
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(new BorderLayout());
        
        // Create game board
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            final int position = i;
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));
            buttons[i].addActionListener(e -> {
                if (client.isMyTurn() && buttons[position].getText().isEmpty()) {
                    client.sendMove(position);
                }
            });
            boardPanel.add(buttons[i]);
        }
        
        // Create status label
        statusLabel = new JLabel("Connecting to server...", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        add(boardPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void updateButton(int position, String mark) 
    {
        SwingUtilities.invokeLater(() -> {
            buttons[position].setText(mark);
        });
    }

    public void updateStatus(String status) {
        SwingUtilities.invokeLater(() -> {
            statusLabel.setText(status);
        });
    }

    public void showMessage(String message) 
    {
        SwingUtilities.invokeLater(() -> {
            statusLabel.setText(message);
        });
    }

    public void disableButtons() 
    {
        SwingUtilities.invokeLater(() -> 
        {
            for (JButton button : buttons) 
            {
                button.setEnabled(false);
            }
        });
    }
}