#include<stdio.h>
#include<stdlib.h>

int main(void)
{
	int inizio, fine, num;
	printf("inserisci l'inizio dell'intervallo: ");
	scanf("%i",&inizio);
	printf("\ninserisci la fine dell'intervallo: ");
	scanf("%i",&fine);
	printf("\nora inserisci un numero: ");
	scanf("%i",&num);
	//inserisco gli esteremi dell'intervallo e il numero prova.
	
	if (num>=inizio and num<=fine)
		printf("0");
	else if (num>fine)
		printf("1");
	else if (num<inizio)
		printf("-1");
	
	//determino le varie possibilità, con i rispettivi output.
}
