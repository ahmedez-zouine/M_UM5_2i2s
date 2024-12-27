import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorServer 
{
    public static void main(String[] args) {
        try {
            // Create the remote object
            CalculatorService calculator = new CalculatorServiceImpl();
            
            // Create the registry on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            
            // Bind the remote object to the registry
            registry.rebind("CalculatorService", calculator);
            
            System.out.println("Calculator Server is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
