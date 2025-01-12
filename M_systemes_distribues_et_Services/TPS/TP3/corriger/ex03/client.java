import java.io.*;
import java.net.*;

public class client {
    private static final String SERVER_ADDRESS = "localhost"; 
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) 
    {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            Thread receiveMessagesThread = new Thread(new ReceiveMessagesTask(in));
            receiveMessagesThread.start();

            System.out.println("Connected to the chat server. Type your messages below:");

            String message;
            while ((message = userInput.readLine()) != null) 
            {
                System.out.println("You: " + message);
                out.println(message); 
            }

        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    private static class ReceiveMessagesTask implements Runnable {
        private BufferedReader in;

        public ReceiveMessagesTask(BufferedReader in) 
        {
            this.in = in;
        }

        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
