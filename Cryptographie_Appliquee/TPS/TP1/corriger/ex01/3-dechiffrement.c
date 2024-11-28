#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int main(int argc, char **argv)
{
    int i = 0;
    int size = strlen(argv[1]);
    int key = 0;
    if (argv[2])
    	key = atoi(argv[2]);
    
    while (i < size)
    {
        char c = argv[1][i];

        c = 'A' + (c - 'A' - key) % 26;
        write(1, &c, 1);
        i++;
    }
    write(1, "\n", 1);
    return 0;
}
