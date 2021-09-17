#include<stdio.h>
#include<stdlib.h>

main()
{
	int num;
	printf("inserisci un numero: ");
	scanf("%d",&num);
	printf("\nil numero scelto e': %d",num);
	printf("\nil numero successivo e': %d",num+1);
	printf("\nil numero precedente e': %d \n",num-1);
	system("PAUSE");
}
