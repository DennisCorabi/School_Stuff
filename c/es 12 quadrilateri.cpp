#include<stdio.h>

int main()
{
	float l1,l2,perimetro,area;
	printf("inserisci i due lati: ");
	scanf("%f%f",&l1,&l2);
	if (l1==l2)
	{
		printf("il quadrilatero e' un quadrato.");
		printf("\nperimetro: %0.2f",l1*4);
		printf("\narea: %0.2f",l1*l1);
	}
	else
	{
		printf("il quadrilatero e' un rettangolo.");
		printf("\nperimetro: %0.2f",(l1+l2)*2);
		printf("\narea: %0.2f",l1*l2);
	}
}
