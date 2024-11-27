public class Main {
    public static void main(String[] args) {
        
	Tortue tortue = new Tortue("Tortue", 100);
        Lievre lievre = new Lievre("Lièvre", 100);
        
        Thread threadTortue = new Thread(tortue);
        Thread threadLievre = new Thread(lievre);
        
        System.out.println("La course va commencer!");
        
        threadTortue.start();
        threadLievre.start();
    }
}
