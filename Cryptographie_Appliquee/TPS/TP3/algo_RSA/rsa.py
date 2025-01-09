from cryptography.hazmat.primitives.asymmetric import rsa, padding
from cryptography.hazmat.primitives import serialization, hashes

# Key Generation
private_key = rsa.generate_private_key(
    public_exponent=65537,  # Common choice for e
    key_size=2048,          # Key size in bits
)
public_key = private_key.public_key()

# Serialize keys to PEM format
private_pem = private_key.private_bytes(
    encoding=serialization.Encoding.PEM,
    format=serialization.PrivateFormat.PKCS8,
    encryption_algorithm=serialization.NoEncryption(),
)
public_pem = public_key.public_bytes(
    encoding=serialization.Encoding.PEM,
    format=serialization.PublicFormat.SubjectPublicKeyInfo,
)

print("Private Key:")
print(private_pem.decode())
print("Public Key:")
print(public_pem.decode())

# Encryption
message = b"Hello, RSA!"
ciphertext = public_key.encrypt(
    message,
    padding.OAEP(
        mgf=padding.MGF1(algorithm=hashes.SHA256()),
        algorithm=hashes.SHA256(),
        label=None,
    ),
)
print("Ciphertext:", ciphertext.hex())

# Decryption
plaintext = private_key.decrypt(
    ciphertext,
    padding.OAEP(
        mgf=padding.MGF1(algorithm=hashes.SHA256()),
        algorithm=hashes.SHA256(),
        label=None,
    ),
)
print("Decrypted Message:", plaintext.decode())
