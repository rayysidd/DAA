#include <stdio.h>
#include <time.h>

int main()
{
    clock_t start, end;
    double cpu_time_used;

    start = clock();

    // Code to measure
    for (int i = 0; i < 1000000; i++)
    {
        int x = i * i;
        printf("%d\n", x);
    }

    end = clock();

    // Calculate elapsed time in seconds
    cpu_time_used = ((double)(end - start)) / CLOCKS_PER_SEC;
    printf("Runtime: %f seconds\n", cpu_time_used);

    return 0;
}
