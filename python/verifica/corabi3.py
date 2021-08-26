import os
import sys

def parametri():
    if len(sys.argv)>=2:
        return sys.argv[1]
    else:
        print("inserisci il PID del processo!")
        sys.exit()

def findsons(pid,cont):
    processi=list()
    lista=os.listdir("/proc")
    for processo in lista:
        if processo.isdigit():
            f=open("/proc/"+processo+"/status")
            lista2=f.read()
            lista2=lista2.split("\n")
            for riga in lista2:
                if len(riga)>0:
                    riga=riga.split("\t")
                    if riga[0]=="Pid:":
                        prox=riga[1]
                    if riga[0]=="PPid:" and riga[1]==pid:
                        print("il processo di PID "+prox+" e' figlio del processo "+riga[1]+".")
                        cont=cont+1
                        findsons(prox,cont)
    return cont

def main():
    pid=parametri()
    cont=0
    cont=findsons(pid,cont)
    if cont==0:
        print("non sono stati trovati processi parenti.")
    else:
        print("sono stati trovati ",cont," processi parenti (figli, nipoti, pronipoti, ecc..)")
    #SBAGLIATO!!!! HO FATTO L'INVERSO DI QUELLO CHE ERA RICHIESTO


if __name__=="__main__":
    main()