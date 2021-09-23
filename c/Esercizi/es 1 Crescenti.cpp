#include<stdio.h>
#include<stdlib.h>

int main(void)
{
	int num1,num2,num3;
	printf("inserisci i tre numeri: ");
	scanf("%i%i%i",&num1,&num2,&num3);
	if (num1<num2 and num2<num3)
		printf("0");
	else if (num1>num2 and num2>num3)
		printf("1");
	else
		printf("-1");
}	
