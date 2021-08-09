import os 
import sys


def parametri():
    if len(sys.argv)==3:
        return sys.argv[1],sys.argv[2]
    else: 
        print ("inserisci due parametri: il primo la stringa da ricercare ed il file passato.")
        sys.exit()


def main(): 
    stringa,file=parametri()
    cont=0
    if os.path.isfile(file)==False:                 #se il file, dato il percorso che viene dato come parametro, non esiste, ne crea uno nuovo
        print("il file scelto non esiste...creazione di un nuovo file di nome",file,".")
        f=open(file,"w")                #creo il file
        sys.exit()                      #esco dal programma perchè tanto vale cercare una stringa in un file appena creato (ovvero vuoto)
    f=open(file,"r")                    #nel caso il file esistesse, allora lo si apre in mod. lettura
    s=list()
    s=f.read()                          #copio il contenuto del file in una lista

    if stringa in s:                    #se la stringa e' presente nel vettore.
        print("trovata la stringa \"",stringa,"\".")
    else:
        print("la stringa ricercata non e' stata trovata.")
if __name__=="__main__":
    main()