#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#define N 100
int main(void)
{
	int const num_numeri=10;
	int vett[N],i,i2,max=0,min,cmax,cmin,temp;
	srand(time(NULL));
	for (i=0;i<num_numeri;i++)
	{
		if (i==0)
		{
			vett[i]=rand();
			min=vett[i];
		}
		else
		{
			vett[i]=rand();
			for(i2=i;i2<=0;i2--)
			{
				if (vett[i]==vett[i2])
					i--;
			}
			if (vett[i]>=max)
				max=vett[i];	
			if (vett[i]<=min)
				min=vett[i];
		}
	}
	printf("valore minimo: %d\n",min);
	printf("Valore massimo: %d",max);
	for (i=0;i<num_numeri;i++)
	{
		if (vett[i]==max)
			cmax=i;
		if (vett[i]==min)
			cmin=i;
	}
	temp=vett[cmax];
	vett[cmax]=vett[cmin];
	vett[cmin]=temp;
	for (i=0;i<num_numeri;i++)
		printf("\nnumero %d del vettore: %d",i,vett[i]);
	

}
