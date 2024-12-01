#### Exercice 6 : Carré de Polybe

```
   1  2  3  4  5
1  A  B  C  D  E
2  F  G  H I/J K
3  L  M  N  O  P
4  Q  R  S  T  U
5  V  W  X  Y  Z

```

Encoding with the Polybius Square:

**To encode "HELLO":**
- H → (2,3)
- E → (1,5)
- L → (3,1)
- L → (3,1)
- O → (3,4)
So, "HELLO" becomes: **23 15 31 31 34**

Decoding:
To decode a message, you reverse the process, using the pairs of numbers to find the corresponding letters.
