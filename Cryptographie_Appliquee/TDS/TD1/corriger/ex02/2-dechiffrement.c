#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

void do_chiffrement(int nb, char c, int *j)
{
	if (c >= 'a' && c <= 'z')
	{
		 c = (c - 'a' - nb ) % 26 + 'a';
		(*j)++;
	}
	else if (c >= 'A' && c <= 'Z')
	{
		c = (c - 'A' - nb ) % 26 + 'A';
		(*j)++;
	}
	write(1, &c , 1);
}

void ft_chiffrement_vgn(char *words, char *key)
{
	int i, j;
	i = 0;
	j = 0;

	int len_words = strlen(words);
	int len_key = strlen(key);
	while (i < len_words)
	{
		char c = words[i];
		char keychar = key[j % len_key];
		//printf("\n------char get from key is %c--------\n",keychar);
		int nbshifft = keychar - 'A';
		do_chiffrement(nbshifft, c, &j);
		i++;
	}
}

int main() {
    char plaintext[] = "PQVTVX RXHWGRX";
    char key[] = "CODE";

    printf("Texte clair : %s\n", plaintext);
    printf("key : %s\n", key);
    printf("Texte chiffre : \n");
    
   ft_chiffrement_vgn(plaintext, key);

    return 0;
}
