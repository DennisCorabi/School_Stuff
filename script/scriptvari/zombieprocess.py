import os

def trova_status(elemento):
    f=open("/proc/"+elemento+"/status","r")         #apro il file 'status' presente in ogni cartella che vado ad analizzare
    pid=f.read()                                    #leggo il file
    pid=pid.split("\n")                             #divido ogni riga del file grazie allo split("\n")
    status=list()
    for element in pid:
        if "State" in element:                      #controllo ogni riga del file, se nella riga c'è scritto "state", allora divido il contenuto della riga rispetto allo spazio ("\t") e lo inserisco in un altro array
            element=element.split("\t")
            status.append(element)
    for element in status:                  #per ogni elemento della riga
        for stat in element:                #per ogni carattere di ogni elemento della riga
            if stat[0]=="Z":               # se l'elemento della riga incomincia con "Z", allora il processo e' zombie         
                print("il processo di PID "+elemento+" e' zombie.")
                check=True
            else:
                check=False
    return check

def main():
    lista=os.listdir("/proc")               #metto nella lista il contenuto di /proc
    lista2=list()
    for element in lista:
        if element[0].isdigit():            #se l'elemento nella lista è una cifra, allora chiama la funzione che stampa il suo stato.
            check=trova_status(element)     #ritorno 'check' per vedere se sono stati trovati processi zombi 
    if check:
        print("sono stati trovati dei processi zombie.")
    else:
        print("non sono stati trovati processi zombie.")
if __name__=="__main__":
    main()
