#include <stdio.h>
#define N 100

int main(void)
{
	int listNum[N], i=0,max=0, min=0, i2=0, cont1=0, cont2=0;
	printf("inserisci i numeri: ");
	while (i<4)
	{
		scanf("%i",&listNum[i]);
		i++;
	}
	//inserisco i valori in una lista.

	i=0;
	min=listNum[0];
	//considero il primo valore della lista come il valore minimo di default.
	printf("\ni numeri scelti sono: ");
	while (i<4)
	{
		printf("%i; ",listNum[i]);
		if (listNum[i]>=max)
		{
			max=listNum[i2];
			cont1=i2;
			//controllo se un valore della lista � maggiore del numero pi� grande precedentemente ottenuto.
		}
		if (listNum[i]<=min)
		{
			min=listNum[i];
			cont2=i;
			//controllo se un valore della lista � minore del numero pi� piccolo precedentemente ottenuto.
		}
		i2++;
		i++;
	}
	//determino il valore massimo e il valore minimo  tra i quattro numeri scelti.

	listNum[N]-=listNum[cont1];
	listNum[N]-=listNum[cont2];
	//tolgo dalla lista i valori massimi e minimi, cosi che rimangano solo i valori intermedi.

	if (listNum[0]>listNum[1])
		printf("\nnumeri ordinati in modo crescente: %d; %d; %d; %d",min,listNum[1],listNum[0],max);
	else if (listNum[1]>=listNum[0])
		printf("\nnumeri ordinati in modo crescente: %d; %d; %d; %d",min,listNum[0],listNum[1],max);
	/*determino, grazie ad un if/else if per trovare il valore maggiore fra i due intermedi.
	dopodich� stampo in ordine crescente i numeri.*/




}
