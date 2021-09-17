#include<stdio.h>

int main(void)
{
	int divisore, dividendo, qz1, resto=0, cont=1;
	float qz2;
	printf("inserisci il dividendo: ");
	scanf("%d",&dividendo);
	printf("\ninserisci il divisore: ");
	scanf("%d",&divisore);
	if (dividendo%divisore==0)
		qz1=dividendo/divisore;
	else 
	{
		qz1=dividendo/divisore;
		resto=dividendo-(qz1*divisore);
	}
	
	for (cont;cont<=qz1;cont++)
		{
			dividendo-=divisore;
			printf("\nsottrazione numero %d: %d",cont,dividendo);
		}
	printf("\nquoziente: %d",qz1);
	if (resto<0)
		resto=resto*(-1);
	printf("\nresto: %d",resto);
}
