#include<stdio.h>
#include<string.h>
#define N 11
int main(void)
{
	int temp,i,y,cont=0,giocata;
	
	do
	{
		printf("Inserisci il numero di giocate fatte (il numero deve essere positivo): ");
		scanf("%d",&temp);
	}while (temp<=0);
	
	int const giocate=temp;
	
	struct s_totocalcio
	{
		int codice;
		char data[N];
	}partite[giocate];
	
	for (i=0;i<giocate;i++)
	{
		printf("inserisci il codice della giocata: ");
		scanf("%d",&partite[i].codice);
		printf("inserisci la data in cui si e' affettuata la giocata numero %d (in formato DD/MM/AAAA): ",partite[i].codice);
		scanf("%s",partite[i].data);
	}
	
	printf("ora che giocata vuoi ricercare? (inserisci il codice): ");
	scanf("%d",&giocata);
	for (i=0;i<giocate;i++)
	{
		if (partite[i].codice==giocata)
		{
			printf("data in cui si e' effettuata la giocata: %s",partite[i].data);
			cont++;
		}
	}
	if (cont==0)
		printf("non e' stato trovata una giocata corrispondente al codice inserito.");
	
}
