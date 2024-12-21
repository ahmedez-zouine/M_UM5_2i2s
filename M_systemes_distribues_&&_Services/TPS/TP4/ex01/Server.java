import java.io.*;
import java.net.*;
import java.util.concurrent.locks.ReentrantLock;

public class Server {
    private ServerSocket serverSocket;
    private String[] board;
    private Player[] players;
    private volatile Player currentPlayer;
    private final ReentrantLock gameLock;
    private volatile boolean gameStarted;
    
    public Server() {
        board = new String[9];
        players = new Player[2];
        for (int i = 0; i < 9; i++) {
            board[i] = "";
        }
        gameLock = new ReentrantLock();
        gameStarted = false;
    }
    
    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            
            // Accept first player
            Socket player1Socket = serverSocket.accept();
            System.out.println("Player 1 connected");
            players[0] = new Player(player1Socket, "X", this);
            players[0].sendMessage("WAIT|Waiting for player 2");
            
            // Accept second player
            Socket player2Socket = serverSocket.accept();
            System.out.println("Player 2 connected");
            players[1] = new Player(player2Socket, "O", this);
            
            // Start the game
            currentPlayer = players[0];
            gameStarted = true;
            
            // Start player threads
            players[0].start();
            players[1].start();
            
            // Notify both players that game has started
            broadcast("START|" + players[0].getMark());
            System.out.println("Game started");
            
        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
    
    public boolean makeMove(int position, Player player) {
        gameLock.lock();
        try {
            // Check if it's valid move
            if (!gameStarted || player != currentPlayer || position < 0 || position > 8 || !board[position].isEmpty()) {
                return false;
            }
            
            // Make the move
            board[position] = player.getMark();
            broadcast("MOVE|" + position + "|" + player.getMark());
            
            // Check for win or draw
            if (checkWinner()) {
                broadcast("WIN|" + player.getMark());
                gameStarted = false;
                return true;
            }
            
            if (isBoardFull()) {
                broadcast("DRAW");
                gameStarted = false;
                return true;
            }
            
            // Switch turns
            currentPlayer = (player == players[0]) ? players[1] : players[0];
            broadcast("TURN|" + currentPlayer.getMark());
            return true;
            
        } finally {
            gameLock.unlock();
        }
    }
    
    private boolean checkWinner() {
        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (!board[i].isEmpty() && board[i].equals(board[i+1]) && board[i].equals(board[i+2])) {
                return true;
            }
        }
        
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (!board[i].isEmpty() && board[i].equals(board[i+3]) && board[i].equals(board[i+6])) {
                return true;
            }
        }
        
        // Check diagonals
        if (!board[0].isEmpty() && board[0].equals(board[4]) && board[0].equals(board[8])) {
            return true;
        }
        if (!board[2].isEmpty() && board[2].equals(board[4]) && board[2].equals(board[6])) {
            return true;
        }
        
        return false;
    }
    
    private boolean isBoardFull() {
        for (String cell : board) {
            if (cell.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    private void broadcast(String message) {
        for (Player player : players) {
            if (player != null) {
                player.sendMessage(message);
            }
        }
    }
    
    public static void main(String[] args) {
        Server server = new Server();
        server.start(1234);
    }
}

class Player extends Thread {
    private Socket socket;
    private String mark;
    private Server server;
    private PrintWriter out;
    private BufferedReader in;
    private volatile boolean connected;
    
    public Player(Socket socket, String mark, Server server) {
        this.socket = socket;
        this.mark = mark;
        this.server = server;
        this.connected = true;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Player Error: " + e.getMessage());
            connected = false;
        }
    }
    
    public String getMark() {
        return mark;
    }
    
    public void sendMessage(String message) {
        if (connected && out != null) {
            out.println(message);
        }
    }
    
    @Override
    public void run() {
        try {
            String message;
            while (connected && (message = in.readLine()) != null) {
                try {
                    int position = Integer.parseInt(message);
                    server.makeMove(position, this);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid move format: " + message);
                }
            }
        } catch (IOException e) {
            System.out.println("Player disconnected: " + e.getMessage());
        } finally {
            connected = false;
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error closing socket: " + e.getMessage());
            }
        }
    }
}