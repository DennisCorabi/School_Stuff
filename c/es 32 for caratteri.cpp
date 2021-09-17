#include<stdio.h>
int main(void)
{
	int num,i=1;
	char c1,c2,c3;
	printf("inserisci i tre caratteri scelti: ");
	fflush(stdin);
	scanf("%c %c %c",&c1,&c2,&c3);
	printf("insersci quante volte vuoi riptere il ciclo: ");
	scanf("%d",&num);
	for (i;i<=num;i++)
		printf("\n%c; %c; %c; ",c1,c2,c3);
}
