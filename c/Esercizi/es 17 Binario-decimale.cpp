#include<stdio.h>
#include<math.h>

int main()
{
	int Nbit, i=0,binario,decimale=0;
	printf("inserisci il numero di bit: ");
	scanf("%d",&Nbit);
	while (i<Nbit)
	{
		printf("\nBit numero %d: ",i);
		scanf("%d",&binario);
		if (binario!=0 && binario!=1)
		{
			while (true)
			{
				printf("\ninserisci uno \"0\" o un \"1\": ");
				scanf("%d",&binario);
				if (binario==0 || binario==1)
					break;
			}
		}
		decimale+=binario*pow(2,i);
		i++;
		
	}
	printf("Decimale convertito: %d",decimale);
}
