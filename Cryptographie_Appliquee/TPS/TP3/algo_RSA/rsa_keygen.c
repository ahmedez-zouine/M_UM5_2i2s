#include <stdio.h>
#include <stdlib.h>
#include <openssl/rsa.h>
#include <openssl/pem.h>
#include <openssl/err.h>

// Fonction pour générer une paire de clés RSA
RSA *generate_rsa_keypair(int key_length) {
    RSA *rsa = RSA_new();
    BIGNUM *e = BN_new();

    // On utilise l'exposant public classique 65537
    BN_set_word(e, 65537);

    // Génération des clés RSA
    if (RSA_generate_key_ex(rsa, key_length, e, NULL) != 1) {
        fprintf(stderr, "Erreur lors de la génération des clés RSA\n");
        RSA_free(rsa);
        BN_free(e);
        return NULL;
    }

    BN_free(e);
    return rsa;
}

// Fonction pour sauvegarder une clé RSA dans un fichier
void save_rsa_key_to_file(RSA *rsa, const char *filename, int is_private) {
    FILE *file = fopen(filename, "w");
    if (!file) {
        perror("Erreur lors de l'ouverture du fichier");
        return;
    }

    if (is_private) {
        PEM_write_RSAPrivateKey(file, rsa, NULL, NULL, 0, NULL, NULL);
    } else {
        PEM_write_RSA_PUBKEY(file, rsa);
    }

    fclose(file);
}

int main() {
    // Initialisation de OpenSSL
    OpenSSL_add_all_algorithms();
    ERR_load_crypto_strings();

    // Génération d'une paire de clés RSA de 2048 bits
    RSA *rsa = generate_rsa_keypair(2048);
    if (!rsa) {
        fprintf(stderr, "Erreur lors de la génération des clés RSA\n");
        return 1;
    }

    // Sauvegarde de la clé publique
    save_rsa_key_to_file(rsa, "public_key.pem", 0);
    printf("Clé publique sauvegardée dans public_key.pem\n");

    // Sauvegarde de la clé privée
    save_rsa_key_to_file(rsa, "private_key.pem", 1);
    printf("Clé privée sauvegardée dans private_key.pem\n");

    // Nettoyage
    RSA_free(rsa);
    EVP_cleanup();
    ERR_free_strings();

    return 0;
}
