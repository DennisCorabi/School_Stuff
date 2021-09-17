#include<stdio.h>

int main(void)
{
	int numero=0,cont=0;
	for (cont=0;cont<=100;)
	{
		if (numero%2==0)
		{
			printf("\n%d",numero);
			cont++;
		}
		numero++;
	}
}
