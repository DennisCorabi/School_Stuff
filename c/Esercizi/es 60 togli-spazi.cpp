#include<stdio.h>
#include<string.h>
#define N 100
void togli_spazi(char stringa[],int cont);
int main(void)
{
	char stringa[N+1];
	int cont;
	printf("inserisci la stringa: ");
	gets(stringa);
	cont=strlen(stringa);
	togli_spazi(stringa,cont);
	printf("stringa senza spazi: %s",stringa);
}
void togli_spazi(char stringa[],int cont)
{
	int i=0,y=0,temp;
	for (i=0;i<cont;i++)
	{
		if (stringa[i]==' ')
		{
			for (y=i;y<cont;y++)
			{
				temp=stringa[y];
				stringa[y]=stringa[y+1];
				stringa[y+1]=temp;
			}
		}
	}
}
