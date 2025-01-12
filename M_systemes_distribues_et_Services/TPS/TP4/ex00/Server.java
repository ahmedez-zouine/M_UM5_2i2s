// Server.java
import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.file.*;

class ChatRoom {
    private String name;
    private Set<ClientHandler> clients = new HashSet<>();

    public ChatRoom(String name) {
        this.name = name;
    }

    public void addClient(ClientHandler client) {
        clients.add(client);
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public boolean containsClient(ClientHandler client) {
        return clients.contains(client);
    }

    public String getName() {
        return name;
    }
}

class ClientHandler implements Runnable {
    private Socket socket;
    private Server server;
    private PrintWriter out;
    private BufferedReader in;
    private String username;
    private ChatRoom currentRoom;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            // Authentication
            if (!authenticate()) {
                return;
            }

            String message;
            while ((message = in.readLine()) != null) {
                if (message.startsWith("/join ")) {
                    String roomName = message.substring(6);
                    server.joinRoom(this, roomName);
                } else if (currentRoom != null) {
                    // Save message to file
                    server.saveMessage(currentRoom.getName(), username, message);
                    // Broadcast to room
                    currentRoom.broadcast(username + ": " + message, this);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (currentRoom != null) {
                currentRoom.removeClient(this);
            }
        }
    }

    private boolean authenticate() throws IOException {
        out.println("Enter username:");
        username = in.readLine();
        out.println("Enter password:");
        String password = in.readLine();

        if (server.authenticateUser(username, password)) {
            out.println("Authentication successful!");
            return true;
        } else {
            out.println("Authentication failed!");
            return false;
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public String getUsername() {
        return username;
    }

    // Add the missing getter method
    public ChatRoom getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(ChatRoom room) {
        this.currentRoom = room;
    }
}

public class Server {
    private ServerSocket serverSocket;
    private Map<String, ChatRoom> chatRooms = new HashMap<>();
    private static final String CREDENTIALS_FILE = "credentials.txt";
    private static final String MESSAGES_DIR = "messages/";

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            
            // Create messages directory if it doesn't exist
            Files.createDirectories(Paths.get(MESSAGES_DIR));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket, this);
                new Thread(clientHandler).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean authenticateUser(String username, String password) {
        try {
            List<String> credentials = Files.readAllLines(Paths.get(CREDENTIALS_FILE));
            String userCredentials = username + ":" + password;
            return credentials.contains(userCredentials);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void joinRoom(ClientHandler client, String roomName) {
        ChatRoom room = chatRooms.computeIfAbsent(roomName, ChatRoom::new);
        
        if (client.getCurrentRoom() != null) {
            client.getCurrentRoom().removeClient(client);
        }
        
        room.addClient(client);
        client.setCurrentRoom(room);
        client.sendMessage("Joined room: " + roomName);
        
        // Load previous messages
        try {
            List<String> messages = Files.readAllLines(Paths.get(MESSAGES_DIR + roomName + ".txt"));
            for (String message : messages) {
                client.sendMessage(message);
            }
        } catch (IOException e) {
            // Ignore if no previous messages exist
        }
    }

    public void saveMessage(String roomName, String username, String message) {
        try {
            Files.write(
                Paths.get(MESSAGES_DIR + roomName + ".txt"),
                Collections.singletonList(username + ": " + message),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(12345);
        server.start();
    }
}
