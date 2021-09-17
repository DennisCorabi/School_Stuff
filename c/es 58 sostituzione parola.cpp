#include<string.h>
#include<stdio.h>

int main(void)
{
	char frase[81],parola1[81],parola2[81];
	int i=0,i2=0,yesorno=0,cont=0,lungparola1;
	do
	{
		printf("inserisci la frase (per mettere uno spazio digita '-'): ");
		scanf("%s",frase);
	}while(strlen(frase)>81);
	for (i=0;i<strlen(frase);i++)
	{
		if (frase[i]=='-')
			frase[i]=' ';
	}
	printf("inserisci la parola da ricercare nella frase: ");
	scanf("%s",parola1);
	lungparola1=strlen(parola1);
	for (i=0;i<strlen(frase);i++)
	{
		do
		{
			if (parola1[i2]!=frase[i+i2])
				yesorno=1;
			i2++;
			cont++;
		}while(yesorno==1 or i2>lungparola1);
		if (cont==lungparola1)
			printf("nella frase presente la parola cercata.\n");
		else
			printf("nella frase non e' presente la parola cercata.\n");
		i2=0;
		cont=0;
		yesorno=0;
	}
}
