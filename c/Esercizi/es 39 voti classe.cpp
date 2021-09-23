#include<stdio.h>
#include<stdlib.h>
#define num_voti 9
#define N 100

int main(void)
{
	int alunni,cont,cont2,i=0;
	float media,voto,somma=0, medie[N];
	printf("quanti alunni ci sono nella 3AI? ");
	scanf("%d",&alunni);
	for (cont=1;cont<=alunni;cont++)
	{
		for(cont2=1;cont2<=num_voti;cont2++)
		{
			printf("\ninserisci voto numero %d dello studente numero %d: ",cont2,cont);
			scanf("%f",&voto);
			somma+=voto;
		}
		media=float(somma)/float(num_voti);
		medie[i]=media;
		i++;
		cont2=1;
		somma=0;
	}
	system("CLS");
	somma=0;
	for (i=0;i<alunni;i++)
	{
		printf("media dello studente numero %d: %0.2f\n",i+1,medie[i]);
		somma+=medie[i];
	}
	printf("\nmedia generale degli studenti: %0.2f\n\n",somma/alunni);
	system("PAUSE");
}
