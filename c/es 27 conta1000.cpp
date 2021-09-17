#include<stdio.h>
int main(void)
{
    int somma=0,cont=0, numero;
    do
    {
        printf("\ninserisci il numero %d: ",cont+1);
        scanf("%d",&numero);
        somma+=numero;
        cont++;
    }   while (somma<1000);
    printf("\nsono stati inseriti %d numeri.",cont);
    
}