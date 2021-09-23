#include<stdio.h>
#define PI 3.14
void blocco(float *a)
{
	while (*a<=0)
	{
		printf("\ninserisci un numero positivo! ");
		scanf("%f",&*a);
	}
}
float quadrato(float a)
{
	float sup;
	sup=a*a;
	return sup;
}
float cerchio(float a)
{
	float sup;
	sup=PI*a*a;
	return sup;
}
int main(void)
{
	int scelta;
	float lato,area;
	while(scelta!=0)
	{
		printf("inserisci il lato: ");
		scanf("%f",&lato);
		blocco(&lato);
		printf("come vuoi considerarlo? \n0:esci dal programma\n1:Lato di un quadrato\n2:Raggio di una circonferenza\nScelta: ");
		scanf("%d",&scelta);
		switch(scelta)
		{
			case 1:
				area=quadrato(lato);
				printf("area del quadrato: %0.2f\n\n",area);
				break;
			case 2:
				area=cerchio(lato);
				printf("area della circonferenza: %0.2f\n\n",area);
				break;
			case 0:
				break;
			default:
				printf("inserisci 1 o 2! ");
		}
	}
}
