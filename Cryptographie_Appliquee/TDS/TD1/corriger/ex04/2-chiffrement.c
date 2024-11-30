#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[])
{
	int i = 0;
	int len = strlen(argv[1]);

	while (i < len)
	{
		char c = argv[1][i];
		if (c >= 'a' &&  c <= 'z')
		{
			if (c < 'n')
				c = c + 13;
			else
				c = (c - 'a' - 13 ) % 26 + 'a';
		}
		else if (c >= 'A' && c <= 'Z')
		{
			if (c < 'N')
				c = c + 13;
			else
				c = (c - 'A' - 13 ) % 26 + 'A';
		}
		write(1, &c , 1);
		i++;
	}
}
