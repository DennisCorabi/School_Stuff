#include<stdio.h>

int main(void)
{
	int macchina, cilindrata;
	printf("che vettura stai usando? (1=autovettura; 2=camion): ");
	while (true)
	{
		scanf("%i",&macchina);
		if (macchina==1 or macchina==2)
			break;
		else
			printf("\ninserisci un \"1\" o un \"2\": ");
	}
	//controllo che l'utente scelga una delle due opzioni, e non altre.
	
	if (macchina==1)
	{
		printf("\ninserisci la cilindrata: ");
		scanf("%i",&cilindrata);
		if (cilindrata>0 && cilindrata<=1000)
			printf("cilindrata = %i; costo = 20 euro.",cilindrata);
		else if (cilindrata<=2000)
			printf("cilindrata = %i; costo = 30 euro.",cilindrata);
		else
			printf("cilindrata = %i; costo = 40 euro.",cilindrata);
	}
	//caso in cui la vettura scelta è la macchina.
	
	else if (macchina==2)
	{
		printf("\ninserisci la cilindrata: ");
		scanf("%i",&cilindrata);
		if (cilindrata>=0 && cilindrata<2000)
			printf("cilindrata = %i; costo = 40 euro.",cilindrata);
		else if (cilindrata<=3000)
			printf("cilindrata = %i; costo = 50 euro.",cilindrata);
		else
			printf("cilindrata = %i; costo = 100 euro.",cilindrata);
	}
	//caso in cui la vettura scelta è un camion.
	
	
}
