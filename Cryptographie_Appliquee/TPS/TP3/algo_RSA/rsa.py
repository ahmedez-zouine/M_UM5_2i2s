from cryptography.hazmat.primitives.asymmetric import rsa
from cryptography.hazmat.primitives import serialization

# Generate a private key
private_key = rsa.generate_private_key(
    public_exponent=65537,  # Common choice for e
    key_size=2048,          # Key size in bits
)

# Generate the public key
public_key = private_key.public_key()

# Serialize the private key to PEM format
private_pem = private_key.private_bytes(
    encoding=serialization.Encoding.PEM,
    format=serialization.PrivateFormat.PKCS8,
    encryption_algorithm=serialization.NoEncryption(),  # No password protection
)

# Serialize the public key to PEM format
public_pem = public_key.public_bytes(
    encoding=serialization.Encoding.PEM,
    format=serialization.PublicFormat.SubjectPublicKeyInfo,
)

# Save the private key to a file
with open("private_key.pem", "wb") as f:
    f.write(private_pem)

# Save the public key to a file
with open("public_key.pem", "wb") as f:
    f.write(public_pem)

print("Private key saved to private_key.pem")
print("Public key saved to public_key.pem")from cryptography.hazmat.primitives import serialization

# Generate a private key
private_key = rsa.generate_private_key(
    public_exponent=65537,  # Common choice for e
    key_size=2048,          # Key size in bits
)

# Generate the public key
public_key = private_key.public_key()

# Serialize the private key to PEM format
private_pem = private_key.private_bytes(
    encoding=serialization.Encoding.PEM,
    format=serialization.PrivateFormat.PKCS8,
    encryption_algorithm=serialization.NoEncryption(),  # No password protection
)

# Serialize the public key to PEM format
public_pem = public_key.public_bytes(
    encoding=serialization.Encoding.PEM,
    format=serialization.PublicFormat.SubjectPublicKeyInfo,
)

# Save the private key to a file
with open("private_key.pem", "wb") as f:
    f.write(private_pem)

# Save the public key to a file
with open("public_key.pem", "wb") as f:
    f.write(public_pem)

print("Private key saved to private_key.pem")
print("Public key saved to public_key.pem"from cryptography.hazmat.primitives.asymmetric import rsa
      from cryptography.hazmat.primitives import serialization

      # Generate a private key
      private_key = rsa.generate_private_key(
              public_exponent=65537,  # Common choice for e
                  key_size=2048,          # Key size in bits
                  )

      # Generate the public key
      public_key = private_key.public_key()

      # Serialize the private key to PEM format
      private_pem = private_key.private_bytes(
              encoding=serialization.Encoding.PEM,
                  format=serialization.PrivateFormat.PKCS8,
                      encryption_algorithm=serialization.NoEncryption(),  # No password protection
                      )

      # Serialize the public key to PEM format
      public_pem = public_key.public_bytes(
              encoding=serialization.Encoding.PEM,
                  format=serialization.PublicFormat.SubjectPublicKeyInfo,
                  )

      # Save the private key to a file
      with open("private_key.pem", "wb") as f:
          f.write(private_pem)

      # Save the public key to a file
      with open("public_key.pem", "wb") as f:
          f.write(public_pem)

      print("Private key saved to private_key.pem")
      print("Public key saved to public_key.pem"))encryption_algorithm=serialization.NoEncryption(),
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
