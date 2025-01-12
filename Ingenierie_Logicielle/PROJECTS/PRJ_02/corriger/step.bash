# Create a project directory
mkdir software_metrics_analysis
cd software_metrics_analysis

# Create subdirectories for each language
mkdir c_program java_program python_program

# Create the C program (c_program/calculator.c)
cat > c_program/calculator.c << 'EOF'
#include <stdio.h>
int somme(int a, int b) {
    return a + b;
}
int produit(int a, int b) {
    return a * b;
}
int main() {
    int x = 5, y = 10;
    printf("Somme : %d\n", somme(x, y));
    printf("Produit : %d\n", produit(x, y));
    return 0;
}
EOF

# Create the Java program (java_program/Calculatrice.java)
cat > java_program/Calculatrice.java << 'EOF'
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
EOF

# Create the Python program (python_program/calculator.py)
cat > python_program/calculator.py << 'EOF'
def addition(a, b):
    return a + b

def multiplication(a, b):
    return a * b

if __name__ == "__main__":
    x, y = 5, 10
    print("Addition :", addition(x, y))
    print("Multiplication :", multiplication(x, y))
EOF
