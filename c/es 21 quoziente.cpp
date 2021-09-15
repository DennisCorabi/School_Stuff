#include<stdio.h>

int main(void)
{
	int num1,num2,cont=0,quoz;
	printf("inserisci il numero da dove vuoi partire: ");
	scanf("%d",&num1);
	printf("\nora inserisci il numero che vuoi raggiungere: ");
	scanf("%d",&num2);
	
	if (num1%num2!=0)
	{
		printf("\nil numero non e' divisibile, lo si arrotonda alla cifra divisibile piu' vicina.");
		while (true)
		{
			num2++;
			if (num1%num2==0)
				break;
		}
		quoz=num1/num2;
	}
		
	while (true)
	{
		num1=num1*quoz;
		if (num1==num2);
			break;
		cont++;
	}
	printf("\nil numero e' stato diviso %d volte.",cont);
}
