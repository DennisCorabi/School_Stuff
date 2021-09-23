#include<stdio.h>
#define N 100

int main(void)
{
	int vet[N],cont,i=0,i2=0,num,indici[N];
	printf("contatore: ");
	scanf("%d",&cont);
	printf("inserisci il numero per la prova: ");
	scanf("%d",&num);
	for (i=0;i<cont;i++)
	{
		printf("inserisci il numero %d: ",i+1);
		scanf("%d",&vet[i]);
	}
	for (i=0;i<cont;i++)
	{
		if (vet[i]==num)
		{
			indici[i2]=i+1;
			i2++;
		}
	}
	if (i2!=0)
	{
		printf("numero trovato alle posizioni ");
		for (i=0;i<i2;i++)
		{
			if (i!=i2-1)
				printf("%d - ",indici[i]);
			else
				printf("%d",indici[i]);
		}
	}
	else
		printf("numero non trovato.");
}
