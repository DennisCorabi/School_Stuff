#include<stdio.h>
#include<stdlib.h>
#define N 100
int main(void)
{
	int lista[N], contp=0, contn=0, i=0, cont2, cont;
	char ch,ch2;
	printf("il ciclo funziona finche' non inserisci 'e' \n ");
	while (ch!='e')
	{
		printf("inserisci il numero %i: ",i+1);
		scanf("%i",&lista[i]);
		if (lista[i]>0)
		{
			contp+=1;
		}
		if (lista[i]<0)
		{
			contn+=1;
		}
		i++;
		ch=getchar();
	}
	cont2=i-1;
	i=0;
	ch=0;
	printf("i numeri scelti sono i seguenti: ");
	while (i<cont2)
	{
		printf("%i; ",lista[i]);
		i++;
	}
	printf("\ni numeri negativi sono %i ",contn);
	printf(", i numeri positivi sono %i ",contp);
	system("PAUSE");
}

