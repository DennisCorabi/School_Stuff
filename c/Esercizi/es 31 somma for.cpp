#include<stdio.h>

int main(void)
{
	int num, somma=0;
	printf("inserisci un numero: ");
	scanf("%d",&num);
	for (num-1;num-1>=0;num--)
		somma+=num-1;
	printf("\nsomma dei numeri precedenti: %d",somma);
}
