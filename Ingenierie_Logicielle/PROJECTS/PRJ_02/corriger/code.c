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
