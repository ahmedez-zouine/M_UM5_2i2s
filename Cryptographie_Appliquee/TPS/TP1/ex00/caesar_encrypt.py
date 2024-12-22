def caesar_encrypt(plaintext, shift):
    result = ""
    for char in plaintext:
        # Ne traiter que les lettres
        if char.isalpha():
            # Déterminer la base (97 pour 'a', 65 pour 'A')
            base = 97 if char.islower() else 65
            # Appliquer le décalage et garder dans l'alphabet (modulo 26)
            new_char = chr((ord(char) - base + shift) % 26 + base)
            result += new_char
        else:
            # Garder les caractères non-alphabétiques tels quels
            result += char
    return result

def caesar_decrypt(ciphertext, shift):
    # Le déchiffrement est un chiffrement avec un décalage négatif
    return caesar_encrypt(ciphertext, -shift)

# Exemple d'utilisation
texte = "Hello, World!"
decalage = 3

texte_chiffre = caesar_encrypt(texte, decalage)
texte_dechiffre = caesar_decrypt(texte_chiffre, decalage)

print(f"Texte original: {texte}")
print(f"Texte chiffré: {texte_chiffre}")
print(f"Texte déchiffré: {texte_dechiffre}")
