#include<stdio.h>

int main(void)
{
	int voto, cont=0, somma=0;
	char scelta;
	float media;
	do
	{
		printf("\ninserisci il voto numero %d: ",cont+1);
		scanf("%d",&voto);
		cont++;
		somma+=voto;
		printf("vuoi continuare? (s/n) ");
		scanf("%c",&scelta);
	}	while (scelta!='s');
	
	media=somma/cont;
	printf("\nsomma voti: %d",somma);
	printf("\nnumero voti inseriti: %d",cont);
	printf("\n media dei voti: %0.2f",media);
}
