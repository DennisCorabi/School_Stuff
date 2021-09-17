#include<stdio.h>
#include<stdlib.h>
#define N 100

int vet[N];
void visualizza_vettore(int a)
{
	int i;
	for (i=0;i<a;i++)
	{
		if (i==a-1)
			printf("%d",vet[i]);
		else
			printf("%d - ",vet[i]);
	}
}

shiftvet(int a)
{
	int i;
	for (i=0;i<a;i++)	
	{
		if (i!=a-1)
			vet[i]=vet[i+1];
		else
			vet[i]=0;
	}
}
int main(void)
{
	int cont,i,scelta;
	do
	{
		printf("inserisci il contatore: ");
		scanf("%d",&cont);
	}while (cont<=0);
	
	for (i=0;i<cont;i++)
	{
		printf("inserisci il numero %d: ",i+1);
		scanf("%d",&vet[i]);
	}
	system("CLS");
	printf("cosa vuoi fare? \n1:Visualizza vettore.\n2:Shifta il vettore di 1 e visualizza il vettore.\nScelta: ");
	scanf("%d",&scelta);
	switch(scelta)
	{
		case 1:
			visualizza_vettore(cont);
			break;
		case 2:
			shiftvet(cont);
			visualizza_vettore(cont);
			break;
	}
}
