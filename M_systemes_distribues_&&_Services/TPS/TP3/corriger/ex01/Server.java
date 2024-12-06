import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            
	    System.out.println("Server is listening on port 12345...");
            
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            String clientMessage = input.readLine();
            System.out.println("Received from client: " + clientMessage);
            output.println("Hello from server!");

            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}
