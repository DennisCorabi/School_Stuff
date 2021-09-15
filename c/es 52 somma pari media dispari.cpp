#include<stdio.h>
#define N 100
int vett[N],cont3=0;
float numeri(int a)
{
	int i,cont2=0,somma=0;
	float media,somma2=0;
	for (i=0;i<a;i++)
	{
		if (i%2!=0)
		{
			somma+=vett[i];
			cont2++;
		}
		else
			somma2+=vett[i];
	}
	media=float(somma)/float(cont2);
	if (cont3==0)
	{
		cont3++;
		return media;
	}
	else
	{
		cont3=0;
		return somma2;
	}
}
int blocco(int a)
{
	while (a<0)
	{
		printf("inserisci un numero positivo! ");
		scanf("%d",&a);
	}
	return a;
}
int main(void)
{
	int i,cont;
	float media,somma;
	printf("inserisci il contatore: ");
	scanf("%d",&cont);
	cont=blocco(cont);
	while (cont!=0)
	{
		for (i=0;i<cont;i++)
		{
			printf("inserisci il numero %d: ",i);
			scanf("%d",&vett[i]);
		}
		media=numeri(cont);
		somma=numeri(cont);
		printf("media dei numeri dispari: %0.2f",media);
		printf("\nsomma dei numeri pari: %d\n\n\n",int(somma));
		printf("inserisci 0 per uscire.\n");
		printf("inserisci il contatore: ");
		scanf("%d",&cont);
		cont=blocco(cont);
	}

}
