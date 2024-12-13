// SimpleHTTPServer.java
import java.io.*;
import java.net.*;

public class SimpleHTTPServer {
    private static final int PORT = 8000;
    
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Serveur demarre sur le port " + PORT);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
                //clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Erreur serveur: " + e.getMessage());
        }
    }
}

class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private static final String FILE_PATH = "content.html";
    
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }
    
    @Override
    public void run() {
        try {
            String content = readContentFromFile();
            
            String httpResponse = "HTTP/1.0 200 OK\n\n" + content;
            
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(httpResponse);
            
            out.close();
            clientSocket.close();
            
        } catch (IOException e) {
            System.err.println("Erreur client: " + e.getMessage());
        }
    }
    
    private String readContentFromFile() 
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            StringBuilder content = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();
            
            return content.toString();
        } catch (IOException e) {
            System.err.println("Erreur lecture fichier: " + e.getMessage());
            return null;
        }
    }
}