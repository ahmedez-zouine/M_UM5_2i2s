### Exercice 5 : Chiffrement Affine

- Chiffrez le texte clair suivant en utilisant la fonction affine : 
- **E(x) = (5x+8) mod 26.**



- Déchiffrez le texte chiffré suivant avec la fonction affine inverse : 
- **D(x) =21(x − 8) mod 26.**

#### Run pgrm

```
gcc 1-Chiffrer.c 
./a.out AFFINE

result:
VUUJIP

-------------------

gcc 2-DeChiffrer.c
./a.out IHHWVCSWFRCP

result:
NSSVARPVCURE

```
