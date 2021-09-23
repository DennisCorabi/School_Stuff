#include<stdio.h>

int main(void)
{
	int cont,cont2=1,num_volte,num,somma=0;
	printf("quanti numeri vuoi inserire? ");
	scanf("%d",&num_volte);
	for (cont=0;cont<num_volte;cont++)
	{
		printf("\ninserisci il numero %d: ",cont2);
		scanf("%d",&num);
		if (num%2!=0)
			somma+=num;
		cont2++;
	}
	printf("Somma dei numeri dispari: %d",somma);
}
