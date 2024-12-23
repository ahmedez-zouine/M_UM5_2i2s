def vigenere_encrypt(plaintext, key):
    ciphertext = []
    key = key.upper()  # La clé en majuscules
    key_index = 0  # Indice de la clé
    
    for char in plaintext:
        if char.isalpha():  # Si le caractère est une lettre
            shift = ord(key[key_index % len(key)]) - ord('A')  # Décalage basé sur la clé
            encrypted_char = chr(((ord(char.upper()) - ord('A') + shift) % 26) + ord('A'))
            ciphertext.append(encrypted_char)
            key_index += 1  # Passer à la lettre suivante de la clé
        else:
            ciphertext.append(char)  # Garder les non-lettres (espaces, ponctuation)
    
    return ''.join(ciphertext)

def vigenere_decrypt(ciphertext, key):
    plaintext = []
    key = key.upper()  # La clé en majuscules
    key_index = 0  # Indice de la clé
    
    for char in ciphertext:
        if char.isalpha():  # Si le caractère est une lettre
            shift = ord(key[key_index % len(key)]) - ord('A')  # Décalage basé sur la clé
            decrypted_char = chr(((ord(char.upper()) - ord('A') - shift + 26) % 26) + ord('A'))
            plaintext.append(decrypted_char)
            key_index += 1  # Passer à la lettre suivante de la clé
        else:
            plaintext.append(char)  # Garder les non-lettres (espaces, ponctuation)
    
    return ''.join(plaintext)

def main():
    print("Chiffrement et déchiffrement de Vigenère")
    
    plaintext = input("Entrez le texte clair : ")
    key = input("Entrez la clé : ")
    
    ciphertext = vigenere_encrypt(plaintext, key)
    print("Texte chiffré :", ciphertext)
    
    decrypted_text = vigenere_decrypt(ciphertext, key)
    print("Texte déchiffré :", decrypted_text)

if __name__ == "__main__":
    main()