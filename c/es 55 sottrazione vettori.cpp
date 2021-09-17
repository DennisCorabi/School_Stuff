#include<stdio.h>
#define N 100

void stampa_valori(int a,int v[])
{
	int i=0;
	for (i=0;i<a;i++)
	{
		if (i==a-1)
			printf("%d",v[i]);
		else
			printf("%d - ",v[i]);
	}
	printf("\n\n");
}
int main(void)
{
	int cont,i,vet1[N],vet2[N],i2;
	do
	{
		printf("Inserisci il contatore: ");
		scanf("%d",&cont);
	} while (cont<=0);
	for (i=0;i<cont;i++)
	{
		printf("inserisci il numero %d del vettore numero 1: ",i+1);
		scanf("%d",&vet1[i]);
		printf("inserisci il numero %d del vettore numero 2: ",i+1);
		scanf("%d",&vet2[i]);
		printf("\n");
	}
	printf("vettore 1: ");
	stampa_valori(cont,vet1);
	printf("vettore 2: ");
	stampa_valori(cont,vet2);
	for (i=0;i<cont;i++)
	{
		for (i2=0;i2<cont;i2++)
		{
			if (vet1[i]==vet2[i2] and vet2[i2]!=0)
				vet1[i]=0;
		}
		if (vet1[i]!=0)
		{
			if (i==cont-1)
				printf("%d",vet1[i]);
			else
				printf("%d - ",vet1[i]);
		}
	}
}
