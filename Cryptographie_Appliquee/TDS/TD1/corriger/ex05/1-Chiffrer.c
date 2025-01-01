// E(x) = (5x+8) mod 26.

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
	int i;
	int len = strlen(argv[1]);
	i = 0;
	while (i < len)
	{
		char m = argv[1][i];
		char c = (5 * m + 8) % 26 + 'A';
		//printf("nb is %d\n",c);
		i++;
		write(1, &c, 1);
	}
}
