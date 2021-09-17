#include<stdio.h>
int main(void)
{
	int somma=0, i=2;
	do
	{
		somma+=(5*i);
		i++;
	} while ((5*i)<=100);
	printf("%d",somma);
}
