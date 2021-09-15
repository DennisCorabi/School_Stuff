#include<stdio.h>
#define N 1000
int main(void)
{
	struct s_stadio
	{
		int anello;
		int zona;
		int settore;
		int orientamento;
		int posto;
	};
	typedef s_stadio prenotanti;
	
	prenotanti posti[N];
	int i=0;
	char scelta;
	FILE *biglietti;
	biglietti=fopen("posti.txt","w");
	
	do
	{
		printf("\nINSERIMENTO NUMERO %d.\n\n",i+1);
		printf("Inserisci l'anello: ");
		scanf("%d",&posti[i].anello);
		
		printf("Inserisci la zona: ");
		scanf("%d",&posti[i].zona);
		
		printf("Inserisci il settore: ");
		scanf("%d",&posti[i].settore);
		
		printf("Inserisci l'orientamento: ");
		scanf("%d",&posti[i].orientamento);
		
		printf("Inserisci il numero del biglietto: ");
		scanf("%d",&posti[i].posto);
		
		fprintf(biglietti,"numero biglietto: %d\n",posti[i].posto);
		fprintf(biglietti,"anello: %d\n",posti[i].anello);
		fprintf(biglietti,"zona: %d\n",posti[i].zona);
		fprintf(biglietti,"settore %d\n",posti[i].settore);
		fprintf(biglietti,"orientamento: %d\n\n",posti[i].orientamento);
		
		printf("tutti le informazioni riguardo all'inserimento numero %d sono state registrate.\n",i+1);
		printf("vuoi continuare ad inserire dati? [S/N]");
		fflush(stdin);
		scanf("%c",&scelta);
		i++;	
	}while (scelta!='N' and scelta!='n');
}
