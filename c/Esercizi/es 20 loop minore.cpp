#include<stdio.h>

int main()
{
	int i=0,numero,min=10;
	while (true)
	{
		printf("inserisci il numero (tentativo numero %d): ",i+1);
		scanf("%d",&numero);
		if (numero<0)
		{
			printf("devi inserire un numero minore o uguale a 0!");
			i--;
		}
		else if (numero==0)
			break;
			
		else
		{
			i++;
			if (numero<min)
				min=numero;
		}
	}
	printf("\n il numero minore e' %d",min);
}
