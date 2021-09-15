#include<stdio.h>
#include<stdlib.h>

int main(void)
{
	int minuti, ore, secondi, s;
	printf("inserisci i secondi: ");
	scanf("%i",&secondi);
	
	minuti=secondi/60;
	//trasformo i secondi in minuti.
	if (minuti<0)
	{
		minuti=0;
	}
	secondi-=(minuti*60);
	//trovo i secondi in eccesso.
	
	ore=minuti/60;
	//trasformo i minuti in ore.
	if (ore<0)
	{
		ore=0;
	}
	minuti-=(ore*60);
	//trovo i minuti rimanenti.
	
	printf("%i ore, %i minuti e %i secondi\n\n",ore,minuti,secondi);
	//stampo il valore espresso in hh/mm/ss.
	
	system("PAUSE");
}
