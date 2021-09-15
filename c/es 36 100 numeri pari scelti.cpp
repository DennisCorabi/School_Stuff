#include<stdio.h>

int main(void)
{
	int numero, cont=0, cicli;
	printf("quanti numeri pari vuoi visualizzati a schermo? ");
	scanf("%d",&cicli);
	for (cont;cont<cicli;)
	{
		if (numero%2==0 and numero!=0)
		{
			printf("%d, ",numero);
			cont++;
		}
		numero++;
	}
}
