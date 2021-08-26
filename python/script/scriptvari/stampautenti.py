import os

def main():
    f=open("/etc/passwd")           #apro passswd
    lista=f.read()                  
    lista=lista.split("\n")         #metto nella lista le singole righe
    for elemento in lista:          #per ogni riga di passwd (non vuota), la splitto rispetto ai ":".
        if len(elemento)>0:
            elemento=elemento.split(":")
            if int(elemento[2])>=1000:          #elemento[2] avremo il UID, mentre in elemento[0] avremo il nome dell'utente
                print(elemento[0])

if __name__=="__main__":
    main()