#include<stdio.h>
#include<math.h>
int area(int lato)
{
	int area;
	area=pow(lato,2);
	return area;
}

int volume(int lato)
{
	int volume;
	volume=pow(lato,3);
	return volume;
}
int main(void)
{
	int lato,sup,vol;
	printf("inserisci il lato di un quadrato: ");
	scanf("%d",&lato);
	sup=area(lato);
	vol=volume(lato);
	printf("\narea del quadrato: %d",sup);
	printf("\nvolume del quadrato: %d",vol);
	
}
