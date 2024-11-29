#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char **argv)
{
	int len = strlen(argv[1]);
	int i = 1;
	char c = 'A';
	while (i <= len)
	{
		char m = argv[1][i-1];
		write(1, &m, 1);
		if (i % 3 == 1)
			c = ('A' - m + 'C') % 26;
		else if (i % 3 == 2)
			c = ('A' - m + 'C') % 26;
		else if (i % 3 == 0)
			c = ('A' - m + 'C') % 26;
		i+= 1;
		write(1, &c, 1);
	}
	write(1, "\n", 1);
}
