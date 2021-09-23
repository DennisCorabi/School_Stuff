#include<stdio.h>

int main(void)
{
	int tempo;
	printf("inserisci il tempo: ");
	scanf("%d",&tempo);
	if (tempo>20)
	{
		while (true)
		{
			printf("\ninserisci un numero minore o uguale a 20! ");
			scanf("%d",&tempo);
			if (tempo<=20)
				break;
		}
	}
	do
	{
		printf("\n%d",tempo);
		tempo--;
	}	while (tempo>=0);
}
