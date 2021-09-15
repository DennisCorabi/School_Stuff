#include<stdio.h>

int main(void)
{
	int voto, somma=0,cont=0;
	do 
	{
		printf("inserisci il voto numero %d: ",cont+1);
		scanf("%d",&voto);
		if (voto<0 or voto>10)
		{
			while (true)
			{
				printf("inserisci un voto positivo e minore o uguale a 10! ");
				scanf("%d",&voto);
				if (voto>0 and voto<=10)
					break;
			}
		}
		somma+=voto;
		cont++;
	}	while (voto!=0);
	printf("\nsomma voti: %d",somma);
	printf("\nnumero voti inseriti: %d",cont-1);
	printf("\nmedia dei voti inseriti: %0.2f",float(somma)/float(cont-1));
}
