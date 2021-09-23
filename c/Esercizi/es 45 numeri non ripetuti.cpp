#include<stdio.h>
#define N 100

int main(void)
{
	int vet[N],i,cont,cont2=0,i2;
	printf("Inserisci il contatore: ");
	scanf("%d",&cont);
	for (i=0;i<cont;i++)
	{
		printf("inserisci il numero %d: ",i+1);
		scanf("%d",&vet[i]);
	}
	for (i=0;i<cont;i++)
	{
		for (i2=0;i2<cont;i2++)
		{
			if (vet[i]==vet[i2] and vet[i]!=0)
			{
				cont2++;
			}
		
		}
		printf("Il numero %d appare %d volte nell'array.\n",vet[i],cont2);
		cont2=0;
		vet[i]=0;
	}
}
