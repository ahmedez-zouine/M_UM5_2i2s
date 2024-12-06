import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server!");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            output.println("Hello from client!");

            String serverResponse = input.readLine();
            System.out.println("Received from server: " + serverResponse);

            socket.close();
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}

