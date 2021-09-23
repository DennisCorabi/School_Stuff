#include<stdio.h>

int main()
{
	float l1,l2,l3;
	printf("inserisci i tre lati (il primo lato e' la base): ");
	scanf("%f%f%f",&l1,&l2,&l3);
	if (l1==l2 and l2==l3)
		printf("il trinagolo e' equilatero");
	else if (l1!=l2 and l1!=l3 and l2==l3)
		printf("il triangolo e' isosciele");
	else if (l1!=l2 and l2!=l3)
		printf("il triangolo e' scaleno");
		
}
