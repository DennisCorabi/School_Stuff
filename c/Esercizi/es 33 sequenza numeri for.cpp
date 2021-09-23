#include<stdio.h>

int main(void)
{
	int num, x, cmag=0, cmin=0, cug=0,i=0, contnum=1;
	//sezione di inserimento dei valori delle variabili
	printf("che numero di prova vuoi inserire? ");
	scanf("%d",&x);
	printf("\nquanti numeri vuoi inserire? ");
	scanf("%d",&i);
	
	//controllo che il contatore sia >0
	if (i<0)
	{
		while (true)
		{
			printf("\ninserisci un numero maggiore di 0! ");
			scanf("%d",&i);
			if (i>=0)
				break;
		}
	}
	
	/*inizio ciclo for: inserisco tot. numeri e successivamente 
	incremento uno dei tre contatori in base ad un rapporto tra il numero inserito ed il numero di prova.*/
	for (i; i>0; i--)
	{
		printf("\ninserisci il numero (ciclo numero %d): ",contnum);
		scanf("%d",&num);
		if (num>x)
			cmag++;
		else if (num<x)
			cmin++;
		else
			cug++;
		contnum++;
	}
	
	//sezione di stampo dei risultati.
	printf("\nsono stati inseriti %d numeri maggiori di %d",cmag,x);
	printf("\nsono stati inseriti %d numeri minori di %d",cmin,x);
	printf("\nsono stati inseriti %d numeri uguali a %d",cug,x);
}
