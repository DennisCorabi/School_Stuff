#include<stdio.h>
#include<stdlib.h>

int main(void)
{
	int n1,n2,somma;
	
	printf("inserisci due valori: ");
	scanf("%i %i",&n1,&n2);
	if (n1%2==0)
		printf("il primo numero e' pari.\n");
	else
		printf("il primo numero e' dispari. \n");
	
	if (n1>0 and n2>0)
	{
	somma=n1+n2;
	printf("la somma dei numeri e' %i",somma);
	}
	else if (n1<0 and n2>0)
	{
		somma=n1*-1+n2;
		printf("\n la somma massima, che si ottiene facendo la somma dei valori assoluti di n1 (%i) e n2 (%i), e' %i",n1*-1,n2,somma);
	}
	else if (n1>0 and n2<0)
	{
		printf("il secondo numero e' negativo \n");
		somma=n1+n2*-1;
		printf("\n la somma massima, che si ottiene facendo la somma dei valori assoluti di n1 (%i) e n2 (%i), e' %i",n1,n2*-1,somma);
	}
	else if (n1<0 and n2<0)
	{
		printf("il secondo numero e' negativo \n");
		somma=(n1+n2)*-1;
		printf("\nla somma massima, che si ottiene facendo la somma dei valori assoluti di n1 (%i) e n2 (%i), e' %i",n1*-1,n2*-1,somma);
	}
}
