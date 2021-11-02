#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/wait.h>
#define N 100


int figli(int from, int to);
int isprime(int num);
int leggiFile();

int main(int argc, char **argv){
	int ncpu, fine,status,blocco;
	pid_t stat,pid;
	FILE *sommo;
	sommo = fopen("blocco.txt","w");		//se esiste il file, lo elimino e ricreo (A.K.A. lo apre in modalità scrittura)
	
	if (argc==3){
		ncpu = atoi(argv[1]);			//leggo dall'argv il primo argomento (numero di core della cpu)
		fine = atoi(argv[2]);			//leggo dall'argv il secondo argomento (fine della sequenza di numeri)
		blocco= fine/ncpu;				
	}
	else
		return 0;
	
	for (int i=0;i<ncpu;i++){
		pid = fork();				//creo un nuovo processo 
		if (pid==0)
			return figli(blocco*i,blocco*(i+1));	//ogni figlio chiamerà SOLO questa funzione, senza creare a loro volta altri figli.
	}
	
	for (int i=0;i<ncpu;i++){
		stat=wait(&status);			//il processo padre aspetta che ogni figlio ha terminato la sua esistenza nel mondo virtuale.
		}
		
	printf("Numeri primi totali trovati: %d\n",leggiFile());			//come ultima istruzione del programma, stampo il totale dei num. primi
	
}


int figli(int from, int to)		//funzione eseguita SOLO dai figli
{
	FILE *sommo;
	int cont=0;
	for (int i=from;i<to;i++){
		if (i%2!=0)				//se il numero è pari, allora non lo provo neanche perchè non sarà sicuramente primo			
			cont+=isprime(i);		//incremento il contatore di 0 o di 1, a seconda del risultato della funzione 'isPrime'
			
	}
	
	sommo =fopen("blocco.txt","a");
	printf("blocco %d->%d: numeri primi trovati: %d\n",from,to,cont);	
	fprintf(sommo,"%d\n",cont);			//inserisco il numero nel file
	return cont;
}	

/*) partendo da 3, per ogni numero controllo se è primo o no
 * (ovvero se non ci sono numeri per il quale è divisibile fino alla sua metà).*/
 
int isprime(int num)
{
	for (int y=3;y<num/2;y+=2){
		if (num%y==0)
			return 0;		// se non è primo, ritorna 0
	}
	return 1; // se è primo, torna 1
}

int leggiFile(){
	FILE * sommo;
	char riga[N];
	int cont=0;
	sommo = fopen("blocco.txt","r");
	
	while (fscanf(sommo,"%s",riga)!=EOF)		//finchè non arriva alla fine del file (EOF), preleva dal file ogni riga
		cont+=atoi(riga);	//incremento il contatore finale
	return cont;
}

