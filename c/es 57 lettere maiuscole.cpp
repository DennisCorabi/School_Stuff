#include<string.h>
#include<stdio.h>

int main(void)
{
	char nome[100],cognome[100];
	int i=0;
	do
	{
		printf("inserisci il nome (senza l'iniziale maiuscola!): ");
		scanf("%s",nome);
	}while (nome[0]<97 or nome[0]>122);
	do
	{
		printf("inserisci il cognome (senza l'iniziale maiuscola): ");
		scanf("%s",cognome);
	}while (cognome[0]<97 or cognome[0]>122);
	nome[0]=nome[0]-32;
	cognome[0]=cognome[0]-32;
	printf("nome: %s",nome);
	printf("  cognome: %s",cognome);
}
