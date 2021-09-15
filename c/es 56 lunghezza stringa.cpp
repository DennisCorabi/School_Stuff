#include<string.h>
#include<stdio.h>

int main(void)
{
	int i=0,spazi=0;
	char stringa[100];
	printf("inserisci la stringa: ");
	scanf("%s",stringa);
	while (stringa[i]!='\0')
		i++;
	printf("la stringa e' lunga %d caratteri.",i);
}
