def caesar_encrypt(plaintext, shift):
    result = ""
    for char in plaintext:
        if char.isalpha():
            base = 97 if char.islower() else 65
            new_char = chr((ord(char) - base + shift) % 26 + base)
            result += new_char
        else:
            result += char
    return result

def caesar_decrypt(ciphertext, shift):
    return caesar_encrypt(ciphertext, -shift)

texte = "Hello, World!"
decalage = 3

texte_chiffre = caesar_encrypt(texte, decalage)
texte_dechiffre = caesar_decrypt(texte_chiffre, decalage)

print(f"Texte original: {texte}")
print(f"Texte chiffré: {texte_chiffre}")
print(f"Texte déchiffré: {texte_dechiffre}")
