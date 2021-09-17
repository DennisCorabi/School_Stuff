#include<stdio.h>

int main(void)
{
	int i=0,cmax=0,cmin=0,cnull=0, cont, numero;
	printf("inserisci il contatore: ");
	scanf("%i",&cont);
	while (i<cont)
	{
		printf("\ninserisci il numero %i: ",i+1);
		scanf("%d",&numero);
		if (i==cont-1 and (numero>0 or numero==0))
		{
			while (true)
			{
				printf("\ninserisci un numero minore di 0 per concludere! ");
				scanf("%i",&numero);
				if (numero<0)
					break;
			}
		}
		
		if (numero>0)
			cmax++;
		else if (numero<0)
			cmin++;
		else
			cnull++;
		i++;
	}
	printf("\nnumeri maggiori: %i",cmax);
	printf("\nnumeri minori: %i",cmin);
	printf("\nnumeri uguali a 0: %i",cnull);
	
}
