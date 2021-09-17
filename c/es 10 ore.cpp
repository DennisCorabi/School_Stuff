#include<stdio.h>
#include<stdlib.h>

int main(void)
{
	int hh1,mm1,hh2,mm2;
	printf("inserisci il primo orario, espresso in ore e minuti.");
	printf("\nore: ");
	scanf("%i",&hh1);
	printf("minuti: ");
	scanf("%i",&mm1);
	//inserisco ore e minuti del primo orario.
	if (mm1>60)
	{
		hh1+=mm1/60;
		mm1=mm1-60*(mm1/60);
	}
	printf("primo orario: %i ore e %i minuti.",hh1,mm1);
	//calcolo il primo orario ---------------------------------------
	
	printf("\n\ninserisci il secondo orario espresso in ore e minuti.");
	printf("\nore: ");
	scanf("%i",&hh2);
	printf("\nminuti: ");
	scanf("%i",&mm2);
	//inserisco ore e minuti del secondo orario.
	if (mm2>60)
	{
		hh2+=mm2/60;
		mm2=mm2-60*(mm2/60);
	}
	printf("secondo orario: %i ore e %i minuti.",hh2,mm2);
	//calcolo il secondo orario --------------------------------------
	if (hh1>=hh2)
	{
		hh1=hh1-hh2;
		if (mm1>=mm2)
		{
			mm1=mm1-mm2;
		}
		else
		{
			mm1=(hh1*60)+mm1;
			mm1=mm1-mm2;
			hh1=mm1/60;
			mm1=mm1-60*hh1;
			   
		}
			printf("\n\ndifferenza di tempo; %i ore e %i minuti",hh1,mm1);
	}
	//primo caso: la prima ora inserita è maggiore della seconda.
	else if (hh1<hh2)
	{
		hh2=hh2-hh1;
		if (mm2>=mm1)
		{
			mm2=mm2-mm1;
		}
		else
		{
			mm2=(hh2*60)+mm2;
			mm2=mm2-mm1;
			hh2=mm2/60;
			mm2=mm2-60*hh2;
			   
		}
		printf("\n\ndifferenza di tempo; %i ore e %i minuti\n\n",hh2,mm2);
	}
	//secondo caso: la seconda ora inserita è maggiore della prima.
	
	system("PAUSE");

}
