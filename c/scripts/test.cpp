#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/wait.h>


int insertNum(int num);

int main(int argc, char ** argv){
	
	pid_t pid;
	FILE * sommo;
	int status,stat,cataloghi=0,num;
	
	if (argc==2){
		cataloghi=atoi(argv[1]);
		sommo = fopen("scarpe.txt","w");		//se il file è gia presente, lo apri in lettura (ovvero indirettamente elimini il suo contenuto e crei un nuovo file)
	}
	else{
		printf("inserisci un parametro!\n");
		return 0;
	}
	
	for (int i=0;i<cataloghi;i++){
		pid =fork();
		if (pid==0){				//se sei un figlio....
			printf("inserisci il numero di scarpe del catalogo %d: ",i+1);
			scanf("%d",&num);
			return insertNum(num);
		}
	}
	
	for (int i=0;i<cataloghi;i++)
		stat=wait(&status);
}

int insertNum(int num){
	FILE * sommo;
	
	sommo=fopen("scarpe.txt","a");
	fprintf(sommo,"%d\n",num);
	return 1;
}


