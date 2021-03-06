#!/usr/bin/python3

import os
def creadirs(percorso):
    lista=percorso.split("/")
    lunglista=len(lista)
    lista.remove(lista[lunglista-1])
    path=""
    for elemento in lista:
        if len(elemento)>0:
            path+=elemento+"/"   
            if os.path.isdir(path)==False:     
                os.mkdir(path)
                print("la directory sussidiaria "+path+" e' stata creata")
    
def creadir(directory):
    if os.path.isdir(directory):
        print("la diretory "+directory+" e' gia' presente.")

    else:
        if "/" in directory:
            creadirs(directory)
        os.mkdir(directory)
        print("la directory "+directory+" e' stata creata.")


    

def creafile(file):
    if os.path.isfile(file):
        print("il file "+file+" e' gia esistente.")
    else:
        if "/" in file:
            creadirs(file)
        os.system("touch "+file)
        print("il file "+file+" e' stato creato.")
                


def main():
    """
    apro il file e per ogni riga definisco se creare una cartella o un file.
    """
    f=open("tutto","r")
    lista=f.read()
    lista=lista.split("\n")
    
    for elemento in lista:
        if len(elemento)>0:
            elemento=elemento.split(" ")
            if elemento[0]=="d":
                print("DIRECTORY DA CREARE: "+elemento[1])
                creadir(elemento[1])
            elif elemento[0]=="f":
                print("FILE DA CREARE: "+elemento[1])
                creafile(elemento[1])
            else:
                print("estensione inserita: "+elemento[0])
                print("inserisci \"f\" per i file o \"d\" per le directory.")
        print("\n")


if __name__=="__main__":
    main()
