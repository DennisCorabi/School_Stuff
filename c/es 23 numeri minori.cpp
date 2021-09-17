#include<stdio.h>

int main(void)
{
	int numero;
	printf("inserisci il numero massimo: ");
	scanf("%i",&numero);
	while (numero>1)
	{
		numero--;
		if (numero%2==0)
			printf("\n%i",numero);
	}
}
