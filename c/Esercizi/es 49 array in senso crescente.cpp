#include<stdio.h>
#define N 100

int main(void)
{
	int vet1[N],vet2[N],vet3[N],cont1,cont2,i=0,i2=0;
	printf("inserisci il contatore numero 1: ");
	scanf("%d",&cont1);
	for (i=0;i<cont1;i++)
	{
		printf("inserisci il numero %d: ",i+1);
		scanf("%d",&vet1[i]);
		if (i!=0)
		{
			if (vet1[i]<vet1[i-1])
			{
				printf("questo array e' ordinato in senso crescente!\n\n");
				i--;
			}
		}
	}
	printf("inserisci il contatore numero 2: ");
	scanf("%d",&cont2);
	for (i=0;i<cont2;i++)
	{
		printf("inserisci il numero %d: ",i+1);
		scanf("%d",&vet2[i]);
		if (i!=0)
		{
			if (vet2[i]<vet2[i-1])
			{
				printf("questo array e' ordinato in senso crescente!\n\n");
				i--;
			}
		}
	}
	
}
