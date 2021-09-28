#include<stdio.h>
#include<unistd.h>
#include<sys/wait.h>

int primi=0;

void IsPrime(int pid);
int main(void)
{
    int ncpu=2;
    int nchildren;
    int ultimo=0;
    int status;
    pid_t pid;

    for (int i=0;i<ncpu;i++)
    {
        if ((pid=fork())==0)       //CREO FIGLI
        {
            IsPrime(getpid());
            printf("processo in esecuzione di pid %d\tParente di %d\n",getpid(),getppid());
            sleep(3);
        }
    }
    do
    {
        ultimo=wait(&status);
        printf("sono stati creati %d processi con PID pari.\n",primi);
    } while (ultimo>0);
    
}

void IsPrime(int pid)
{
    if (pid%2==0)
        primi++;
}


