import os
import sys
import datetime
def parametri():
    if len(sys.argv)>=2:
        return sys.argv[1]
    else:
        print("inserisci il PID del processo!")
        sys.exit()

def find_user(uid):
    f=open("/etc/passwd")
    lista=f.read()
    lista=lista.split("\n")
    for element in lista:
        if len(element)>0:
            element=element.split(":")
            if element[2]==uid:
                print("nome dell'utente a cui appartiene il processo: "+element[0])

def speculazione(processi):
    cont=0
    prox=0
    for elemento in processi:
        if elemento.isdigit():
            prox=prox+1
            f=open("/proc/"+elemento+"/status")
            lista=f.read()
            lista=lista.split("\n")
            for riga in lista:
                riga=riga.split("\t")
                if riga[0]=="Speculation_Store_Bypass:":
                    riga[1]=riga[1].split(" ")
                    for stringa in riga[1]:
                        if stringa=="vulnerable":
                            print("il processo di PID "+elemento+" e' è sensibile al problema “Speculation_Store_Bypass”.")
                            cont=cont+1
    print("numero processi: ",prox,"\tnumero vulnerabili: ",cont)
                
def creazione(prox,lista):
    for elemento in lista:
        if elemento==prox:
            info=os.stat("/proc/"+elemento)
    print("data di creazione del processo dall'unix epoch: ",datetime.datetime.fromtimestamp(info[7]))
          
def main():
    pid=parametri()
    processi=list()
    cont=0
    lista=os.listdir("/proc")
    for element in lista:
        if pid==element:
            f=open("/proc/"+pid+"/status")
            creazione(pid,lista)
            cont=cont+1
    if cont==0:
        print("Non e' stato trovato nessun processo associato al PID inserito.")
        sys.exit()
    

    lista2=f.read()
    lista2=lista2.split("\n")
    for element in lista2:
        element=element.split("\t")

        if element[0]=="Uid:":
            print("UID del processo: "+element[1])
            uid=element[1]

        if element[0]=="PPid:":
            print("Ppid del processo: "+element[1])
            
        if element[0]=="Speculation_Store_Bypass:":
            element[1]=element[1].split(" ")
            for stringa in element[1]:
                if stringa=="vulnerable":
                    print("questo processo e' è sensibile al problema “Speculation_Store_Bypass”.")

    find_user(uid)
    speculazione(lista)

if __name__=="__main__":
    main()