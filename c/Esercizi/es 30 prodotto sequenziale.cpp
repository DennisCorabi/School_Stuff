#include<stdio.h>

int main(void)
{
	int num1,num2,cont=0,prodotto=0;
	printf("\ninserisci il valore del primo numero: ");
	scanf("%d",&num1);
	if (num1<=0)
	{
		while (true)
		{
			printf("\ninserisci un numero positivo! ");
			scanf("%d",&num1);
			if (num1>0)
				break;
		}
	}
	
	printf("\ninserisci il valore del secondo numero: ");
	scanf("%d",&num2);
	if (num2<=0)
	{
		while (true)
		{
			printf("\ninserisci un numero positivo! ");
			scanf("%d",&num2);
			if (num2>0)
				break;
		}
	}
	
	while (cont<num2-1)
	{
		prodotto+=num1;
		cont++;
		printf("\n%d",prodotto);
	}
	printf("\nprodotto finale: %d",prodotto+=num1);
}
