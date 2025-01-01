def scytale_encrypt(plaintext, key):
    """Chiffre un texte clair en utilisant le chiffrement Scytale."""
    plaintext = plaintext.replace(" ", "").upper()
    num_cols = key
    num_rows = len(plaintext) // num_cols + (1 if len(plaintext) % num_cols != 0 else 0)
    
    grid = [['' for _ in range(num_cols)] for _ in range(num_rows)]
    idx = 0
    for r in range(num_rows):
        for c in range(num_cols):
            if idx < len(plaintext):
                grid[r][c] = plaintext[idx]
                idx += 1
    ciphertext = ''.join([grid[r][c] for c in range(num_cols) for r in range(num_rows) if grid[r][c] != ''])
    
    return ciphertext

def scytale_decrypt(ciphertext, key):
    """Déchiffre un texte chiffré en utilisant le chiffrement Scytale."""
    num_cols = key
    num_rows = len(ciphertext) // num_cols
    grid = [['' for _ in range(num_cols)] for _ in range(num_rows)]
    idx = 0
    for c in range(num_cols):
        for r in range(num_rows):
            if idx < len(ciphertext):
                grid[r][c] = ciphertext[idx]
                idx += 1
    plaintext = ''.join([grid[r][c] for r in range(num_rows) for c in range(num_cols)])
    
    return plaintext

def main():
    print("Chiffrement Scytale")
    plaintext = input("Entrez le texte clair : ")
    key = int(input("Entrez la clé (nombre de colonnes) : "))
    ciphertext = scytale_encrypt(plaintext, key)
    print("Texte chiffré :", ciphertext)
    decrypted_text = scytale_decrypt(ciphertext, key)
    print("Texte déchiffré :", decrypted_text)
if __name__ == "__main__":
    main()