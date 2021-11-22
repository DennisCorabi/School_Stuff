#include<unistd.h>
#include<stdio.h>

int main(void)
{
	pid_t pid;
	pid=fork();
	printf("%d\n",pid);
	if (pid==0)
		printf("figlio\n");
	else
		printf("padre\n");
}
