#include<stdio.h>

int main()
{
	int numero, cont=0;
	while (true)
	{
		printf("inserisci numero (tentativo numero %i): ",cont+1);
		scanf("%d",&numero);
		if (numero==0)
			break;
		cont++;
	}
	printf("\nsono stati scelti %d numeri (Zero escluso)",cont);
}
