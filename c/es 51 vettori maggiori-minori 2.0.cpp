#include<stdio.h>
#define N 100

int vet1[N],vet2[N],vet3[N];

void stampo_valori(int a, int b, int c)
{
	if (b!=c-1)
		printf("%d - ",a);
	else
		printf("%d",a);
}
int main(void)
{
	int cont,i,num,i2=0,i3=0,scelta;
	printf("quanti numeri vuoi nel vettore? ");
	scanf("%d",&cont);
	printf("su che numero vuoi fare la prova? ");
	scanf("%d",&num);
	for (i=0;i<cont;i++)
	{
		printf("inserisci il numero %d: ",i+1);
		scanf("%d",&vet1[i]);
		if (vet1[i]<num)
		{
			vet2[i2]=vet1[i];
			i2++;
		}
		else if (vet1[i]>num)
		{
			vet3[i3]=vet1[i];
			i3++;
		}
	}
	printf("\nora cosa vuoi fare?\n0:Esci dal programma.\n1:Stampa a video i numeri maggiori del numero dato.\n2:Stampa i minori.\nScelta: ");
	scanf("%d",&scelta);
	switch(scelta)
	{
		case 1:
			for (i=0;i<i3;i++)
				stampo_valori(vet3[i],i,i3);
			break;
		case 2:
			for (i=0;i<i2;i++)
				stampo_valori(vet2[i],i,i2);
			break;
		case 0:
			break;
		default:
			printf("inserisci 0 - 1 - 2");
	}
}
