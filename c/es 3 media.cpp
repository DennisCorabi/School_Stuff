#include<stdio.h>
#include<stdlib.h>
#define N 100
int main()
{
	int i=0, lista[N], num2=1, somma=0, cont2;
	float media, contatore2;
	printf("l'inserimento termina quando inserisci 0. \n");
	while (num2!=0)
	{
		i++;
		printf("inserisci numero %i: ",i);
		scanf("%d",&lista[i]);
		somma+=lista[i];
		contatore2+=lista[i];
		num2=lista[i];
	}
	cont2=i;
	i=0;
	printf("\nsono stati scelti %i numeri.",cont2);
	printf("\ni numeri scelti sono: ");
	while (i<cont2)
	{
		i++;
		printf("%i; ",lista[i]);
	}
	
	printf("\nla somma dei numeri e' %i",somma);
	media=contatore2/i;
	printf("\nla media dei numeri e' %f \n",media);
	system("PAUSE");
	
}
