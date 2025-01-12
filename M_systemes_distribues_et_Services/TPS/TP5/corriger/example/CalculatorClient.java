import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            // Get the registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            
            // Look up the remote object
            CalculatorService calculator = (CalculatorService) registry.lookup("CalculatorService");
            
            // Use the remote object
            System.out.println("10 + 5 = " + calculator.add(10, 5));
            System.out.println("10 - 5 = " + calculator.subtract(10, 5));
            System.out.println("10 * 5 = " + calculator.multiply(10, 5));
            System.out.println("10 / 5 = " + calculator.divide(10, 5));
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
