#include<stdio.h>

float Tmax[31], Tmin[31];
int const giorni=5;

int blocco(int a)
{
	while (a!=2 and a!=1)
	{
		printf("\ninserisci 1 o 2! ");
		scanf("%d",&a);
	}
	return a;
}
int stampo_max()
{
	int i=1,scelta2;
	float somma=0,media;
	for (i=1;i<=giorni;i++)
	{
		printf("%d gennaio: %0.2f\n",i,Tmax[i-1]);
		somma+=Tmax[i-1];
	}
	media=somma/giorni;
	printf("vuoi visualizzare anche la media di questi valori? (1:SI) (2:NO)\nScelta: ");
	scanf("%d",&scelta2);
	scelta2=blocco(scelta2);
	if (scelta2==1)	
	{
		printf("media delle temperature massime di gennaio: %0.2f",media);
	}
	printf("\n\n\n");
}
int stampo_min()
{
	int i=1,scelta2;
	float somma=0,media;
	for (i=1;i<=giorni;i++)
	{
		printf("%d gennaio: %0.2f\n",i,Tmin[i-1]);
		somma+=Tmin[i-1];
	}
	media=somma/giorni;
	printf("vuoi visualizzare anche la media di questi valori? (1:SI) (2:NO)\nScelta: ");
	scanf("%d",&scelta2);
	scelta2=blocco(scelta2);
	if (scelta2==1)	
	{
		printf("media delle temperature minime di gennaio: %0.2f",media);
	}
	printf("\n\n\n");
}
int differenza(void)
{
	int i,giorno_max;
	float differenza,max;
	for(i=1;i<giorni;i++)
	{
		if (i==1)
			max=Tmax[i-1]-Tmin[i-1];
		else
			differenza=Tmax[i-1]-Tmin[i-1];
		if (differenza>=max)
		{
			max=differenza;
			giorno_max=i;
		}
	}
	printf("La differenza massima e' stata registrata il %d Gennaio, con una differenza di %0.2f gradi celsius.\n\n\n",giorno_max,max);
}
int main(void)
{
	int i=1,i2=1,scelta=1;
	for (i=1;i<=giorni;i++)
	{
		printf("inserisci la temperatura massima del %d Gennaio: ",i);
		scanf("%f",&Tmax[i-1]);
		printf("inserisci la temperatura minima del %d Gennaio: ",i);
		scanf("%f",&Tmin[i-1]);
		if (Tmax[i-1]<=Tmin[i-1])
		{
			printf("una temperatura minima non puo' essere maggiore della massima!\n");
			i--;
		}
		printf("\n");
	}
	while (scelta!=0)
	{
		printf("Ora cosa vuoi fare? \n1:visualizzare le temperature massime.\n2: visualizzare le temperature minime.\n3:visualizzare il giorno in cui si e' avuta meno differenza tra la Tmax e Tmin.\nper uscire, inserisci 0.\nScelta: ");
		scanf("%d",&scelta);
		switch(scelta)
		{
			case 1:
				stampo_max();
				break;
			case 3:
				differenza();
				break;
			case 2:
				stampo_min();
				break;
		}
	}
}
