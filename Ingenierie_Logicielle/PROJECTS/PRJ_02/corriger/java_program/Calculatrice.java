public class Calculatrice {
    public int addition(int a, int b) {
        return a + b;
    }
    public int multiplication(int a, int b) {
        return a * b;
    }
    public static void main(String[] args) {
        Calculatrice calc = new Calculatrice();
        System.out.println("Addition : " + calc.addition(5, 10));
        System.out.println("Multiplication : " + calc.multiplication(5, 10));
    }
}
