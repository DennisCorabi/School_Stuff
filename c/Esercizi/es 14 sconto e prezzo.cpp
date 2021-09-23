#include<stdio.h>

int main(void)
{
	int sconto;
	float spesa,prezzo;
	printf("inserisci la spesa fatta: ");
	scanf("%f",&spesa);
	if (spesa<50)
		sconto=5;
	else if (spesa<65)
		sconto=6;
	else if (spesa <80)
		sconto=7;
	else if (spesa>100)
		sconto=10;
	prezzo=spesa-((spesa/100)*sconto);
	printf("lo sconto applicato e' %i per cento; il prezzo scontato e' %0.2f euro",sconto,prezzo);
}
