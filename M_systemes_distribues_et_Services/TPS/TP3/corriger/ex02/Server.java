import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class SimpleHTTPServer {
    private static final int PORT = 8000;
    private static final int THREAD_POOL_SIZE = 50;
    
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Serveur demarre sur le port " + PORT);
            
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    executor.execute(new ClientHandler(clientSocket));
                } catch (IOException e) {
                    System.err.println("Erreur d'acceptation client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur serveur: " + e.getMessage());
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
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
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String requestLine = in.readLine();
            if (requestLine != null) {
                System.out.println("Received request: " + requestLine);
            }
            
            while (in.readLine().length() > 0) {
                // Do nothing, just read until empty line
            }
            
            String content = readContentFromFile();
            if (content != null) {
                sendResponse(out, "200 OK", content);
            } else {
                sendResponse(out, "404 Not Found", "<html><body><h1>404 - File not found</h1></body></html>");
            }
            
        } catch (IOException e) {
            System.err.println("Erreur client: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Erreur fermeture socket: " + e.getMessage());
            }
        }
    }
    
    private void sendResponse(PrintWriter out, String status, String content) {
        out.println("HTTP/1.1 " + status);
        out.println("Content-Type: text/html");
        out.println("Content-Length: " + content.length());
        out.println();
        out.println(content);
        out.flush();
    }
    
    private String readContentFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            StringBuilder content = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            
            return content.toString();
        } catch (IOException e) {
            System.err.println("Erreur lecture fichier: " + e.getMessage());
            return null;
        }
    }
}