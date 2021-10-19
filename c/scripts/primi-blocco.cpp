#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/wait.h>


int figli(int from, int to);
int isprime(int num);

int main(int argc, char **argv){
	int ncpu, fine,status,blocco;
	pid_t stat,pid;
	FILE *sommo;
	
	if (argc==3){
		ncpu = atoi(argv[1]);
		fine = atoi(argv[2]);
		blocco= fine/ncpu;
	}
	else
		return 0;
	
	for (int i=0;i<ncpu;i++){
		pid = fork();
		if (pid==0)
			return figli(blocco*i,blocco*(i+1));
	}
	
	for (int i=0;i<ncpu;i++){
		stat=wait(&status);
		}
}


int figli(int from, int to)
{
	FILE *sommo;
	int cont=0;
	for (int i=from;i<to;i++){
		if (i%2!=0)
			cont+=isprime(i);
			
	}
	
	sommo =fopen("blocco.txt","w");
	printf("blocco %d->%d: numeri primi trovati: %d\n",from,to,cont);
	fprintf(sommo,"%d",cont);
	return cont;
}

int isprime(int num)
{
	for (int y=3;y<num/2;y+=2){
		if (num%y==0)
			return 0;
	}
	return 1;
}
