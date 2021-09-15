#include<stdio.h>
#include<stdlib.h>
main()
{
	int num1, num2, i=0, max, mcd;
	printf("inserisci il primo numero: ");
	scanf("%i",&num1);
	printf("\n ora inserisci il secondo numero: ");
	scanf("%i",&num2);
	if (num1>num2)
		max=num1;
	else
		max=num2;
	while (i<max)
	{
		i++;
		if (num1%i==0 && num2%i==0)
		{
			printf("\n %i",i);
			mcd=i;
		}
	}
	printf("\nL'MCD e' %i \n",mcd);
	system("PAUSE");
}
