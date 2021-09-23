#include<stdio.h>

int main(void)
{
	int anno, scelta, i=0;
	while (true)
	{
		printf("inserisci l'anno: ");
		scanf("%d",&anno);
		if (anno<0)
			printf("inserisci un anno maggiore di 0!\n");
		else 
		{
			if ((anno%4==0 and anno%100!=0) or anno%400==0)
				printf("l'anno e' bisestile.");
			else
				printf("l'anno non e' bisestile, riprova.\n");
		}
		i++;
		printf("\nvuoi continuare?(si=1, no=0) ");
		scanf("%d",&scelta);
		if (scelta==0)
			break;
		else if (scelta!=0 and scelta!=1)
		{
			while (true)
			{
				printf("inserisci uno \"0\" o un \"1\"! ");
				scanf("%d",scelta);
				if (scelta==1 or scelta==0)
					break;
			}
		}
	
	}
	printf("\nhai ripetuto il ciclo %d volte.",i);
}
