def polybius_encrypt(plaintext):
    """Chiffre un texte clair en utilisant la grille de Polybe."""
    polybius_square = {
        'A': '11', 'B': '12', 'C': '13', 'D': '14', 'E': '15',
        'F': '21', 'G': '22', 'H': '23', 'I': '24', 'J': '24',
        'K': '25', 'L': '31', 'M': '32', 'N': '33', 'O': '34',
        'P': '35', 'Q': '41', 'R': '42', 'S': '43', 'T': '44',
        'U': '45', 'V': '51', 'W': '52', 'X': '53', 'Y': '54', 'Z': '55'
    }
    
    plaintext = plaintext.replace(" ", "").upper()

    ciphertext = []
    
    for char in plaintext:
        if char.isalpha():  # Traiter seulement les lettres alphabétiques
            ciphertext.append(polybius_square[char])
    
    return ' '.join(ciphertext)


def polybius_decrypt(ciphertext):
    """Déchiffre un texte chiffré en utilisant la grille de Polybe."""
    reverse_polybius_square = {
        '11': 'A', '12': 'B', '13': 'C', '14': 'D', '15': 'E',
        '21': 'F', '22': 'G', '23': 'H', '24': 'I', '24': 'J',  # I/J partagent la même case
        '25': 'K', '31': 'L', '32': 'M', '33': 'N', '34': 'O',
        '35': 'P', '41': 'Q', '42': 'R', '43': 'S', '44': 'T',
        '45': 'U', '51': 'V', '52': 'W', '53': 'X', '54': 'Y', '55': 'Z'
    }
    
    # Diviser le texte chiffré en paires de chiffres
    ciphertext = ciphertext.split()
    
    plaintext = []
    
    for code in ciphertext:
        if code in reverse_polybius_square:
            plaintext.append(reverse_polybius_square[code])
    
    return ''.join(plaintext)


def main():
    print("Chiffrement Polybe")
    
    plaintext = input("Entrez le texte clair : ")
    
    ciphertext = polybius_encrypt(plaintext)
    print("Texte chiffré :", ciphertext)
    
    decrypted_text = polybius_decrypt(ciphertext)
    print("Texte déchiffré :", decrypted_text)


if __name__ == "__main__":
    main()
