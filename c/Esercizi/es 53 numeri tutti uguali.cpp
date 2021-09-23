#include<stdio.h>
#define N 100

int main(void)
{
	int vet[N],cont,cont2=0,i;
	do
	{
			printf("inserisci il numero di celle (massimo 30): ");
			scanf("%d",&cont);
	}while (cont<0 or cont>30);
	for (i=0;i<cont;i++)
	{
		printf("inserisci il numero %d: ",i+1);
		scanf("%d",&vet[i]);
	}
	for (i=0;i<cont;i++)
	{
		if (vet[i]==vet[i+1] and i!=cont-1)
			cont2++;
	}
	if (cont2==cont-1)
		printf("tutti i numeri nel vettore sono uguali");
	else
		printf("i numeri nel vettore non sono tutti uguali.");
	
}
