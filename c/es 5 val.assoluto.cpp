#include<stdio.h>
#include<stdlib.h>

int main(void)
{
	int num;
	printf("inserisci un numero: ");
	scanf("%i",&num);
	
	if (num>0)
	{
		printf("valore assoluto del numero: %i",num);
	}
	if (num==0)
	{
		printf("valore assoluto del numero: %i",num);
	}
	if (num<0)
	{
		printf("valore assoluto del numero: %i",num*-1);
	}
}
