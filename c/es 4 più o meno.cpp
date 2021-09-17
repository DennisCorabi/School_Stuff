#include<stdio.h>
#include<stdlib.h>

int main(void)
{
	int n;
	printf("scegli un numero: ");
	scanf("%d",&n);
	if (n>0)
	{
		printf("il numero e' positivo \n");
	}
	else if (n<0)
	{
		printf("il numero e' negativo. \n");
	}
	else if (n==0)
	{
		printf("il numero e' uguale a 0. \n");
	}
}
