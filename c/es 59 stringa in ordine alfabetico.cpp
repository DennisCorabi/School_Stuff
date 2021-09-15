#include<stdio.h>
#include<string.h>
#define N 100
void bubble(char vet[],int lung);
void visualizza(char vet[]);
int main(void)
{
	int lung;
	char stringa[N+1];
	printf("inserisci il tuo nome: ");
	scanf("%s",stringa);
	lung=strlen(stringa);
	bubble(stringa,lung);
	
}

void visualizza(char vet[])
{
	printf("il tuo nome in ordine alfabetico: %s",vet);
}
void bubble(char vet[],int lung)
{
	int i,y,temp;
	for (i=0;i<lung;i++)
	{
		for(y=0;y<lung;y++)
		{
			if (vet[y]>vet[i])
			{
				temp=vet[y];
				vet[y]=vet[i];
				vet[i]=temp;
			}
		}
	}
	visualizza(vet);
}

