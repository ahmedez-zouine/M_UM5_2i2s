def affine_encrypt(plaintext, a, b):
    """Chiffre un texte clair avec le chiffrement affine."""
    ciphertext = []
    for char in plaintext:
        if char.isalpha():  # Si le caractère est une lettre
            x = ord(char.upper()) - ord('A')
            encrypted_char = chr(((a * x + b) % 26) + ord('A'))
            ciphertext.append(encrypted_char)
        else:
            ciphertext.append(char)
    return ''.join(ciphertext)
def affine_decrypt(ciphertext, a, b):
    """Déchiffre un texte chiffré avec le chiffrement affine."""
    a_inv = mod_inverse(a, 26)
    plaintext = []
    for char in ciphertext:
        if char.isalpha():  # Si le caractère est une lettre
            y = ord(char.upper()) - ord('A')
            decrypted_char = chr((a_inv * (y - b) % 26) + ord('A'))
            plaintext.append(decrypted_char)
        else:
            plaintext.append(char)
    return ''.join(plaintext)
def mod_inverse(a, m):
    """Calcul l'inverse modulaire de a modulo m."""
    for x in range(1, m):
        if (a * x) % m == 1:
            return x
    raise ValueError(f"L'inverse modulaire de {a} modulo {m} n'existe pas.")
def gcd(x, y):
    """Calcul du PGCD (plus grand commun diviseur) de x et y."""
    while y:
        x, y = y, x % y
    return x
def main():
    print("Chiffrement Affine")
    plaintext = input("Entrez le texte clair : ")
    a = int(input("Entrez la valeur de a (doit être coprime avec 26) : "))
    b = int(input("Entrez la valeur de b : "))
    if gcd(a, 26) != 1:
        print("Erreur : a doit être coprime avec 26.")
        return
    ciphertext = affine_encrypt(plaintext, a, b)
    print("Texte chiffré :", ciphertext)
    decrypted_text = affine_decrypt(ciphertext, a, b)
    print("Texte déchiffré :", decrypted_text)
if __name__ == "__main__":
    main()
