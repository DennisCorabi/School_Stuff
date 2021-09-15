#include<stdio.h>
#define N 100

int main(void)
{
	int vet1[N],vet2[N],vet3[N],cont,i=0,num,cont2=0,cont3=0;
	printf("quanti numeri vuoi inserire? ");
	scanf("%d",&cont);
	printf("su che numero vuoi fare la prova? ");
	scanf("%d",&num);
	for (i=0;i<cont;i++)
	{
		printf("inserisci il numero %d: ",i+1);
		scanf("%d",&vet1[i]);
	}
	for (i=0;i<cont;i++)
	{
		if (vet1[i]<=num)
		{
			vet2[cont2]=vet1[i];
			cont2++;
		}
		else
		{
			vet3[cont3]=vet1[i];
			cont3++;
		}
	}
	printf("\ni numeri maggiori di %d sono: ",num);
	for (i=0;i<cont3;i++)
	{
		if (i==cont3-1)
			printf("%d",vet3[i]);
		else
			printf("%d - ",vet3[i]);
	}
	printf("\ni numeri minori/uguali di %d sono: ",num);
	for (i=0;i<cont2;i++)
	{
		if (i==cont2-1)
			printf("%d",vet2[i]);
		else
			printf("%d - ",vet2[i]);
	}
}
