#include<stdio.h>

int main(void)
{
	int contatore,somma=0,i=0,numero;
	printf("inserisci il contatore: ");
	scanf("%d",&contatore);
	while (i<contatore)
	{
		printf("\ninserisci il numero %i: ",i+1);
		scanf("%d",&numero);
		somma+=numero;
		i++;
	}
	printf("\nla somma dei numeri inseriti e' %i",somma);
}
