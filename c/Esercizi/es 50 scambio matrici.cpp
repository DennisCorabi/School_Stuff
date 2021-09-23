#include<stdio.h>
#define N 100
#define M 100
int blocco(int a,int b)
{
	while (a>b or a<=0)
	{
		printf("inserisci un numero minoredi %d e maggiore di 0!\nnumero: ",b);
		scanf("%d",&a);
	}
	return a;
}
int main(void)
{
	int nrighe,ncolonne,i,i2,matrice[N][M],temp;
	int const nrmax=10,ncmax=20;
	printf("inserisci il numero di righe: ");
	scanf("%d",&nrighe);
	nrighe=blocco(nrighe,nrmax);
	printf("inserisci il numero di colonne: ");
	scanf("%d",&ncolonne);
	ncolonne=blocco(ncolonne,ncmax);
	printf("\n");
	for (i=0;i<nrighe;i++)
	{
		for (i2=0;i2<ncolonne;i2++)
		{
			printf("inserisci il numero di riga %d e colonna %d: ",i+1,i2+1);
			scanf("%d",&matrice[i][i2]);
		}
		printf("\n\n");
	}
	printf("PRIMA: \n\n");
	for (i=0;i<nrighe;i++)
	{
		for (i2=0;i2<ncolonne;i2++)
		{
			if (i2==0)
				printf("| %d",matrice[i][i2]);	
			else if (i2==ncolonne-1)
				printf(" %d |",matrice[i][i2]);
			else
				printf(" %d",matrice[i][i2]);
		}
		printf("\n\n");
	}
	printf("\n\nDOPO:\n\n");
	for (i=0;i<nrighe;i++)
	{
		for (i2=0;i2<ncolonne;i2++)
		{
			if (i%2==0 and i!=nrighe-1)
			{
				temp=matrice[i][i2];
				matrice[i][i2]=matrice[i+1][i2];
				matrice[i+1][i2]=temp;
			}
		}
		for (i2=0;i2<ncolonne;i2++)
		{
			if (i2==0)
				printf("| %d",matrice[i][i2]);	
			else if (i2==ncolonne-1)
				printf(" %d |",matrice[i][i2]);
			else
				printf(" %d",matrice[i][i2]);
				
				
		}
		printf("\n\n");
	}
}

