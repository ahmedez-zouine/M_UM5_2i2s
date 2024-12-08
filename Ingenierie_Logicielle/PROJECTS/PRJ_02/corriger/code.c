#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Variable globale non initialisée
int* global_ptr;

// Fonction avec une complexité cyclomatique élevée
int complexFunction(int a, int b, int c) {
    int result = 0;
    
    if (a > 0) {
        if (b > 0) {
            if (c > 0) {
                result = a + b + c;
            } else if (c < -10) {
                result = a + b - c;
            } else {
                result = a + b;
            }
        } else if (b < -10) {
            if (c > 0) {
                result = a - b + c;
            } else {
                result = a - b - c;
            }
        }
    } else if (a < -10) {
        if (b > 0) {
            result = -a + b;
        } else {
            result = -a - b;
        }
    }
    
    return result;
}

// Fonction avec fuite de mémoire
char* memoryLeakFunction() {
    char* str = (char*)malloc(100);
    strcpy(str, "Hello World");
    return str;  // La mémoire n'est jamais libérée
}

// Fonction avec dépassement de buffer
void bufferOverflowFunction() {
    char small_buffer[5];
    char* large_string = "This string is too long for the buffer";
    strcpy(small_buffer, large_string);  // Buffer overflow
}

// Fonction avec division par zéro potentielle
int divisionFunction(int a, int b) {
    return a / b;  // Pas de vérification si b == 0
}

// Fonction avec boucle infinie potentielle
void infiniteLoopFunction(int x) {
    while(x > 0) {
        printf("%d\n", x);
        // x n'est jamais modifié
    }
}

// Fonction avec un switch mal formé
void switchFunction(int choice) {
    switch(choice) {
        case 1:
            printf("One");
            // Pas de break
        case 2:
            printf("Two");
            break;
        case 3:
            printf("Three");
            // Pas de break
        default:
            printf("Default");
    }
}

// Fonction utilisant une variable non initialisée
int uninitializedFunction() {
    int x;
    return x + 10;  // x n'est pas initialisé
}

// Fonction avec pointeur null potentiel
void nullPointerFunction(int* ptr) {
    *ptr = 42;  // Pas de vérification si ptr est NULL
}

int main() {
    // Utilisation de variable non initialisée
    int* ptr;
    printf("%d\n", *ptr);
    
    // Fuite de mémoire
    char* str = memoryLeakFunction();
    
    // Buffer overflow
    bufferOverflowFunction();
    
    // Division par zéro potentielle
    int result = divisionFunction(10, 0);
    
    // Appel de fonction avec pointeur null
    nullPointerFunction(NULL);
    
    // Utilisation de variable globale non initialisée
    *global_ptr = 42;
    
    // Appel à la fonction complexe
    int complex_result = complexFunction(5, -15, 7);
    
    // Boucle infinie potentielle
    infiniteLoopFunction(1);
    
    // Switch mal formé
    switchFunction(1);
    
    return 0;  // La mémoire allouée n'est jamais libérée
}
