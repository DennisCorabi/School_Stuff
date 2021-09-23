#include<unistd.h>
#include<stdio.h>
#include<stdlib.h>
#include<sys/wait.h>

int ciao=0;
int child_instructions(void);
int main(void)
{
    pid_t pid, childPid;
    pid=fork();
    if (pid!=0)
    {
        childPid=wait(NULL);
        printf("processo figlio terminato (PID=%d) valore di ritorno: %d \n",childPid,ciao);
    }
}

int child_instructions(void)
{
    sleep(5+rand()%20);
    return 1+rand()%127;
}