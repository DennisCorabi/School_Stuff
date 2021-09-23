#include<stdio.h>
#include<math.h>
void area(int lato, int *a)
{
	*a=pow(lato,2);
}

void volume(int lato, int *a)
{
	*a=pow(lato,3);
}
int main(void)
{
	int lato,sup,vol;
	printf("inserisci il lato di un quadrato: ");
	scanf("%d",&lato);
	area(lato,&sup);
	volume(lato, &vol);
	printf("\narea del quadrato: %d",sup);
	printf("\nvolume del quadrato: %d",vol);
	
}
