#include <stdio.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdint.h>
//#include <time.h>

int baby();
int main()
{
	pid_t pidForked, w;
	int status;
	
	for (int id=0; id<4; id++) 
	{
		sleep(0.01);
		if ((pidForked = fork()) == 0)			//CREO FIGLI
			baby();
	}

	do
	{
		w=wait(&status);
		if(w > 0)
			printf("%i -- %i\n", w, status>>8);
	}while (w>0);
	return 0;

}

int baby()
{
	static int n=0;
	n++;
	printf("PID forked: %jd\n", (intmax_t) getpid());

	srand((unsigned) time (NULL));

	int timer = (rand() % 15 ) + 5;
	sleep(timer);
	srand((unsigned) time (NULL));

	int rcode = (rand() % 126) + 1;
	return rcode;
}